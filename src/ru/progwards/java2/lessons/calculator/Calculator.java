package ru.progwards.java2.lessons.calculator;

import java.util.ArrayList;

public class Calculator {
    public static int calculate(String expression) {
        final int PLUS = Integer.MAX_VALUE;
        final int MINUS = Integer.MIN_VALUE;
        char[] arr = expression.toCharArray();
        int res = 0;
        boolean flag = false;
        ArrayList<Integer> listInt = new ArrayList<>();
        String s;
        for (int i = 1, n = arr.length; i < n; i += 2) {
            s = String.valueOf(arr[i-1]);
            int a = Integer.parseInt(s);
            s = String.valueOf(arr[i+1]);
            int b = Integer.parseInt(s);
            if (!flag) {
                if (arr[i] == '*') {
                    flag = true;
                    res = a * b;
                } else if (arr[i] == '/') {
                    flag = true;
                    res = a / b;
                } else if (arr[i] == '+') {
                    flag = false;
                    listInt.add(a);
                    listInt.add(PLUS);
                } else {
                    flag = false;
                    listInt.add(a);
                    listInt.add(MINUS);
                }
            } else {
                if (arr[i] == '*') {
                    flag = true;
                    res *= b;
                } else if (arr[i] == '/') {
                    flag = true;
                    res /= b;
                } else if (arr[i] == '+') {
                    flag = false;
                    listInt.add(res);
                    listInt.add(PLUS);
                } else {
                    flag = false;
                    listInt.add(res);
                    listInt.add(MINUS);
                }
            }
            if (i == n-2) {
                if (flag) {
                    listInt.add(res);
                } else listInt.add(b);
            }
        }
     //   System.out.println(listInt.toString());
        res = listInt.get(0);
        for (int i = 1, n = listInt.size(); i < n; i += 2) {
            if (listInt.get(i) == PLUS) {
                res += listInt.get(i+1);
            } else res -= listInt.get(i+1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(calculate("5+3*5*6-5/2+7"));
    }
}
