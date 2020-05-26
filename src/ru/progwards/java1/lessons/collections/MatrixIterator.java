package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MatrixIterator<T> implements Iterator<T> {
    private List<T> array;
    private int index;

    MatrixIterator(T[][] array) {
        index = 0;
        this.array = new ArrayList<>();
        for (int i = 0, m = array.length; i < m; ++i) {
            for (int j = 0, n = array[i].length; j < n; ++j) {
                this.array.add(array[i][j]);
            }
        }
    }

    @Override
    public boolean hasNext() {
        if(index < array.size()) return true;
        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) index = 0;
        T value = array.get(index);
        ++index;
        return value;
    }

    public static void main(String[] args) {
        String[] arr1 = {"1", "3", "3", "5", "4", "4", "4", "2", "4"};
        String[] arr2 = {"1", "2", "3", "5", "5", "4", "3"};
        String[][] matr = {arr1, arr2};
        MatrixIterator<String> it = new MatrixIterator<>(matr);
        while (it.hasNext()) {
            String s = it.next();
            System.out.print(s +' ');
        }
    }
}
