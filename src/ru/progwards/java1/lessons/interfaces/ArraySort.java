package ru.progwards.java1.lessons.interfaces;

public class ArraySort implements CompareWeight {
    public static void sort(CompareWeight[] a) {
        CompareWeight tmp;
        for (int i = 0, n = a.length; i < n-1; ++i) {
            for (int j = i+1; j < n; ++j) {
                if (a[i].compareWeight(a[j]) == CompareResult.GREATER) {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return null;
    }
}
