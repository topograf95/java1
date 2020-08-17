package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderProcessor {
    public String startPath;
    List<Order> listOrder;

    public OrderProcessor(String startPath) {
        this.startPath = startPath;
        listOrder = new ArrayList<>();
    }
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        final int[] countError = {0};
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???-??????-????.csv");
        try {
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) {
                        LocalDate ld = LocalDate.ofEpochDay(Files.getLastModifiedTime(path).toMillis()/(24*60*60*1000));
                        if ((start == null && finish == null)||
                                (start == null && ld.compareTo(finish) <= 0)||
                                (finish == null && ld.compareTo(start) >= 0)||
                                (ld.compareTo(start) >= 0 && ld.compareTo(finish) <= 0)) {
                                    String strName = path.getFileName().toString();
                                    String shop = strName.substring(0,3);
                                    if (shopId == null || shopId.compareTo(shop) == 0) {
                                        Order order = new Order();
                                        order.items = new ArrayList<>();
                                        List<String> itemFale = Files.readAllLines(path);
                                        for (String itemStr : itemFale) {
                                            String[] arr = itemStr.split(",");
                                            OrderItem orIt = new OrderItem();
                                            orIt.googsName = arr[0].trim();
                                            orIt.count = Integer.parseInt(arr[1].trim());
                                            orIt.price = Double.parseDouble(arr[2].trim());
                                            order.items.add(orIt);
                                            order.sum += orIt.price * orIt.count;
                                        }
                                        order.items.sort(new Comparator<OrderItem>() {
                                            @Override
                                            public int compare(OrderItem o1, OrderItem o2) {
                                                return (o1.googsName).compareTo(o2.googsName);
                                            }
                                        });
                                        order.shopId = shop;
                                        order.orderId = strName.substring(4,10);
                                        order.customerId = strName.substring(11,15);
                                        order.datetime = LocalDateTime.parse(Files.getLastModifiedTime(path).toString(),
                                                DateTimeFormatter.ISO_DATE_TIME);
                                        listOrder.add(order);
                                    }
                        }
                    } else { countError[0]++;}
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countError[0];
    }
    public List<Order> process(String shopId) {
        listOrder.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return (o1.datetime).compareTo(o2.datetime);
            }
        });
        if (shopId == null) return listOrder;
        List<Order> listOr = new ArrayList<>();
        for (Order lor : listOrder) {
            if (shopId.compareTo(lor.shopId) == 0) listOr.add(lor);
        }
        return listOr;
    }
    public Map<String, Double> statisticsByShop() {
        Map<String, Double> sumByShop = new TreeMap<>();
        for (Order lor : listOrder) {
            Double value = sumByShop.putIfAbsent(lor.shopId, lor.sum);
            if (value != null) {
                sumByShop.put(lor.shopId, value + lor.sum);
            }
        }
        return sumByShop;
    }
    public Map<String, Double> statisticsByGoods() {
        Map<String, Double> sumByGoods = new TreeMap<>();
        for (Order lor : listOrder) {
            for (OrderItem gon : lor.items) {
                Double priceIt = gon.price * gon.count;
                Double value = sumByGoods.putIfAbsent(gon.googsName, priceIt);
                if (value != null) {
                    sumByGoods.put(gon.googsName, value + priceIt);
                }
            }
        }
        return sumByGoods;
    }
    public Map<LocalDate, Double> statisticsByDay() {
        Map<LocalDate, Double> sumByDay = new TreeMap<>();
        for (Order lor : listOrder) {
            LocalDate ld = LocalDate.from(lor.datetime);
            Double value = sumByDay.putIfAbsent(ld, lor.sum);
            if (value != null) {
                sumByDay.put(ld, value + lor.sum);
            }
        }
        return sumByDay;
    }
}
