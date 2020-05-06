package ru.progwards.java1.lessons.interfaces;

enum CompareResult { LESS, EQUAL, GREATER }

public class Food implements CompareWeight {
    private int weight;

    public Food (int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
    @Override
    public CompareResult compareWeight(Food food) {
        if (weight < food.weight) return CompareResult.LESS;
        else if (weight == food.weight) return CompareResult.EQUAL;
        else return CompareResult.GREATER;
    }

    public static void main(String[] args) {
        Food a = new Food(100);
        Food b = new Food(200);
        System.out.println(a.compareWeight(b));
    }
}
