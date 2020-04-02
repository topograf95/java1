package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class ArraySort {
    public static void sort(int[] a) {
        int tmp;
        for (int i = 0, n = a.length; i < n-1; ++i) {
            for (int j = i+1; j < n; ++j) {
                if (a[i] > a[j]) {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] a = {5, 4, 3, 1, 3, 5, 2, 4, 1};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
