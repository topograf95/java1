package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.util.*;

public class SalesInfo {
    ArrayDeque<String> names;
    ArrayDeque<String> product;
    ArrayDeque<Integer> quantity;
    ArrayDeque<Double> cost;

    SalesInfo() {
        names = new ArrayDeque<>();
        product = new ArrayDeque<>();
        quantity = new ArrayDeque<>();
        cost = new ArrayDeque<>();
    }

    public int loadOrders(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            try (Scanner scanner = new Scanner(reader)) {
                while (scanner.hasNextLine()) {
                    String str = scanner.nextLine();
                    String[] arrStr = str.split(",");
                    if (arrStr.length != 4) continue;
                    for (int i = 0; i < 4; ++i) {
                        arrStr[i] = arrStr[i].trim();
                    }
                    if (!(arrStr[2].matches("[0-9]+")) || !(arrStr[3].matches("[0-9]*[.]?[0-9]+")))continue;
                    names.offer(arrStr[0]);
                    product.offer(arrStr[1]);
                    quantity.offer(Integer.valueOf(arrStr[2]));
                    cost.offer(Double.valueOf(arrStr[3]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names.size();
    }

    public Map<String, Double> getGoods() {
        Map<String, Double> productCostSum = new TreeMap<>();
        for (Double ct : cost) {
            String prod = product.pollFirst();
            product.offer(prod);
            Double value = productCostSum.putIfAbsent(prod, ct);
            if (value != null) productCostSum.put(prod, value + ct);
        }
        return productCostSum;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> nameCostQuantity = new TreeMap<>();
        for (Double ct : cost) {
            Integer qt = quantity.pollFirst();
            quantity.offer(qt);
            String name = names.pollFirst();
            names.offer(name);
            var value = nameCostQuantity.putIfAbsent(name, new AbstractMap.SimpleEntry<>(ct, qt));
            if (value != null) nameCostQuantity.put(name,
                    new AbstractMap.SimpleEntry<>(ct + value.getKey(), qt + value.getValue()));
        }
        return nameCostQuantity;
    }

    public static void main(String[] args) {
        SalesInfo si = new SalesInfo();
        System.out.println(si.loadOrders("fileTest.txt"));
        System.out.println(si.names);
        System.out.println(si.product);
        System.out.println(si.quantity);
        System.out.println(si.cost);
        System.out.println(si.getGoods());
        System.out.println(si.getCustomers());
    }
}
