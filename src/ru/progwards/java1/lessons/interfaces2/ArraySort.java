package ru.progwards.java1.lessons.interfaces2;

import java.util.Arrays;

public class ArraySort {
    public static void sort(Comparable<Number>[] a) {
 //       Arrays.sort(a);
        Comparable<Number> tmp;
        for (int i = 0, n = a.length; i < n-1; ++i) {
            for (int j = i+1; j < n; ++j) {
                if (a[i].compareTo((Number)a[j]) > 0) {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Number n1 = new IntNumber(5);
        Number n2 = new IntNumber(1);
        Number nd3 = new DoubleNumber(4.0);
        Number nd4 = new DoubleNumber(3.5);
        Number n5 = new IntNumber(3);
        Number[] numbers = {n1, n2, nd3, nd4, n5};
        sort(numbers);
        for (var nu : numbers) {
            System.out.println(nu.toString());
        }
    }
}
