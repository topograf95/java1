package ru.progwards.java1.lessons.interfaces;

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
        return null;
    }

    @Override
    public CompareResult compareWeight(Food food) {
        if (weight < food.weight) return CompareResult.LESS;
        else if (weight == food.weight) return CompareResult.EQUAL;
        else return CompareResult.GREATER;
    }

    @Override
    public CompareResult compareWeight(Animal animal) {
        return null;
    }

    public static void main(String[] args) {
        Food a = new Food(100);
        Food b = new Food(200);
        System.out.println(a.compareWeight(b));
    }
}
