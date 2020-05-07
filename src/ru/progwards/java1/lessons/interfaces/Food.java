package ru.progwards.java1.lessons.interfaces;

import static ru.progwards.java1.lessons.interfaces.ArraySort.sort;

public class Food implements CompareWeight {
    private int weight;

    public Food (int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        if (weight < ((Food) smthHasWeigt).weight) return CompareResult.LESS;
        else if (weight == ((Food) smthHasWeigt).weight) return CompareResult.EQUAL;
        else return CompareResult.GREATER;
    }


    public static void main(String[] args) {
        Food a = new Food(100);
        Food b = new Food(200);
        Food c = new Food(300);
        System.out.println(a.compareWeight(b));
        CompareWeight[] arr = {b, c, b, c, a};
        sort(arr);
        for (CompareWeight v : arr) {
            System.out.println(((Food) v).getWeight());
        }
    }
}
