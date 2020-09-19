package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class DynamicArray <T> {
    private T[] dynamicArr = (T[]) new Object[0];
    int count_elem = 0;

    public void add(T elem) {
        int current_size = dynamicArr.length;
        if (current_size == 0) {
            dynamicArr = (T[]) new Object[1];
        } else if (current_size == count_elem) {
            T[] tmpArr = dynamicArr;
            dynamicArr = (T[]) new Object[current_size * 2];
            System.arraycopy(tmpArr, 0, dynamicArr, 0, current_size);
        }
        dynamicArr[count_elem] = elem;
        ++count_elem;
    }

    public void insert(int pos, T elem) {
        if (pos < 0 || pos > count_elem) {
            System.out.println("Недопустимое значение pos = "+ pos);
            System.out.println("Введите pos от 0 до "+ count_elem);
            return;
        }
        int current_size = dynamicArr.length;
        if (pos == current_size) {
            add(elem);
            return;
        }
        T[] tmpArr = dynamicArr;
        if (current_size == count_elem) {
            dynamicArr = (T[]) new Object[current_size * 2];
        }
        System.arraycopy(tmpArr, 0, dynamicArr, 0, pos);
        System.arraycopy(tmpArr, pos, dynamicArr, pos+1, count_elem - pos);
        dynamicArr[pos] = elem;
        ++count_elem;
    }

    public void remove(int pos) {
        if (!chekPos(pos)) return;
        System.arraycopy(dynamicArr, pos+1, dynamicArr, pos, count_elem - pos-1);
        dynamicArr[--count_elem] = null;
    }

    public T get(int pos) {
        if (!chekPos(pos)) return null;
        return dynamicArr[pos];
    }

    public int size() {
        return dynamicArr.length;
    }

    private boolean chekPos(int pos) {
        if (count_elem == 0) {
            System.out.println("Массив не содержит элементов!");
            return false;
        } else if (pos < 0 || pos >= count_elem) {
            System.out.println("Недопустимое значение pos = "+ pos);
            System.out.println("Введите pos от 0 до "+ (count_elem - 1));
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DynamicArray {" +
                "dynamicArr= " + Arrays.toString(dynamicArr) +
                ", count_elem= " + count_elem +
                '}';
    }

    public static void main(String[] args) {
        DynamicArray da = new DynamicArray();
        da.insert(0, "7ABC");
 //       da.remove(0);
        System.out.println(da.get(0));
 //       da.remove(0);
        System.out.println(da.toString());
        da.add(5);
        da.add(4);
        da.add(3);
        da.add(2);
        System.out.println(da.toString());
        da.insert(3, 10);
        System.out.println(da.toString());
        da.insert(7, 20);
        System.out.println(da.toString());
        da.insert(3, 15);
        System.out.println(da.toString());
        da.insert(0, 1);
        System.out.println(da.toString());
        da.insert(7, 100);
        System.out.println(da.toString());
        da.remove(0);
        System.out.println(da.toString());
        da.remove(6);
        System.out.println(da.toString());
    }
}
