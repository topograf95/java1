package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;

public class Creator {
    public static Collection<Integer> fillEven(int n) {
        Collection<Integer> collection = new ArrayList<>(n);
        n *= 2;
        for (int i = 2; i <= n; ++i) {
            if (i % 2 == 0) collection.add(i);
        }
        return collection;
    }
    public static Collection<Integer> fillOdd(int n) {
        Collection<Integer> collection = new ArrayList<>(n*3);
        for (int i = n*6; i > 0; --i) {
            if (i % 2 != 0) collection.add(i);
        }
        return collection;
    }
    public static Collection<Integer> fill3(int n) {
        n *= 3;
        Collection<Integer> collection = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            collection.add(i);
            collection.add(++i*i);
            collection.add(++i*i*i);
        }
        return collection;
    }

    public static void main(String[] args) {
        System.out.println(fill3(3).toString());
    }
}
