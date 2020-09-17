package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    List<Goods> listGoods;

    public void setGoods(List<Goods> list) {
        listGoods = new ArrayList<>(list);
    }

    public List<Goods> sortByName() {
        listGoods.sort(Comparator.comparing(a -> a.name));
        return listGoods;
    }

    public List<Goods> sortByNumber() {
        listGoods.sort(Comparator.comparing(a -> a.number.toUpperCase()));
        return listGoods;
    }

    public List<Goods> sortByPartNumber() {
        listGoods.sort(Comparator.comparing(a -> a.number.substring(0, 3).toUpperCase()));
        return listGoods;
    }

    public List<Goods> sortByAvailabilityAndNumber() {
        listGoods.sort(Comparator.comparing(Goods::getAvailable)
                .thenComparing(a -> a.number.toUpperCase()));
        return listGoods;
    }

    public List<Goods> expiredAfter(Instant date) {
        listGoods.sort(Comparator.comparing(a -> a.expired));
        return listGoods.stream().dropWhile(a -> a.expired.compareTo(date) <= 0)
                .collect(Collectors.toList());
    }

    public List<Goods> сountLess(int count) {
        listGoods.sort(Comparator.comparing(Goods::getAvailable));
        return listGoods.stream().takeWhile(x -> x.available < count)
                .collect(Collectors.toList());
    }

    public List<Goods> сountBetween(int count1, int count2) {
        listGoods.sort(Comparator.comparing(Goods::getAvailable));
        return listGoods.stream().dropWhile(a -> a.available <= count1)
                .takeWhile(a -> a.available < count2).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Goods> liG = new ArrayList<>(List.of(
                new Goods("Бананы","ба143",20,59.99, Instant.ofEpochSecond(500000)),
                new Goods("Помидоры","Ба133",10,79.90, Instant.ofEpochSecond(300000)),
                new Goods("Апельсины","ба123",10,49.99, Instant.ofEpochSecond(100000)),
                new Goods("Картофель","Ба113",30,19.90, Instant.ofEpochSecond(200000))
        ));
        GoodsWithLambda gwl = new GoodsWithLambda();
        gwl.setGoods(liG);
        gwl.сountBetween(10, 30).forEach(System.out::println);
        gwl.sortByAvailabilityAndNumber().forEach(System.out::println);
    }
}
