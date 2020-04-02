package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int[] arr;

    public DIntArray() {
        arr = new int[0];
    }
    public void show() {
        System.out.println(Arrays.toString(arr));
    }
    public void add(int num) {
        int[] arrayTmp = arr;
        arr = new int[arr.length + 1];
        System.arraycopy(arrayTmp, 0, arr, 0, arrayTmp.length);
        arr[arr.length - 1] = num;
    }
    public void atInsert(int pos, int num) {
        if (pos >= 0 && pos <= arr.length) {
            int[] arrayTmp = arr;
            arr = new int[arr.length + 1];
            System.arraycopy(arrayTmp, 0, arr, 0, pos);
            System.arraycopy(arrayTmp, pos, arr, pos+1, arrayTmp.length - pos);
            arr[pos] = num;
        } else {
            System.out.println("Error: invalid value pos= "+ pos);
        }
    }
    public void atDelete(int pos) {
        if (pos >= 0 && pos < arr.length) {
            int[] arrayTmp = arr;
            arr = new int[arr.length - 1];
            System.arraycopy(arrayTmp, 0, arr, 0, pos);
            System.arraycopy(arrayTmp, pos+1, arr, pos, arrayTmp.length - pos - 1);
        } else {
            System.out.println("Error: invalid value pos= "+ pos);
        }
    }
    public int at(int pos) {
        if (pos >= 0 && pos < arr.length) {
            return arr[pos];
        } else {
            System.out.println("Error: invalid value pos= "+ pos);
            return pos;
        }
    }

    public static void main(String[] args) {
        DIntArray a1 = new DIntArray();
        a1.show();
        a1.add(5);
        a1.show();
        a1.add(3);
        a1.show();
        a1.add(4);
        a1.show();
        a1.atInsert(1, 7);
        a1.show();
        a1.atDelete(1);
        a1.show();
        System.out.println(a1.at(0));
    }
}
