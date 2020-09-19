package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class ArraySort {
    public static <T> void sort(T[] a) {
        T tmp;
        for (int i = 0, n = a.length; i < n-1; ++i) {
            for (int j = i+1; j < n; ++j) {
                if (((Comparable)(a[i])).compareTo(a[j]) > 0) {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
    public static void main(String[] args) {
        Integer[] a = {5, 4, 3, 1, 3, 5, 2, 4, 1};
        String[] s = {"E", "C", "B", "D", "C", "A", "E"};
        sort(a);
        sort(s);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(s));
    }
}
