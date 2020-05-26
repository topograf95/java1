package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    private int index;

    ArrayIterator(T[] array) {
        this.array = array;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if(index < array.length) return true;
        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) index = 0;
        T value = array[index];
        ++index;
        return value;
    }

    public static void main(String[] args) {
        String[] arr = {"1", "3", "3", "5", "4", "4", "4", "2", "4"};
        ArrayIterator<String> itArr = new ArrayIterator<>(arr);
        while (itArr.hasNext()) {
            String s = itArr.next();
            System.out.print(s +' ');
        }
        System.out.println("\n"+ itArr.next());
        System.out.println(itArr.next());
    }
}
