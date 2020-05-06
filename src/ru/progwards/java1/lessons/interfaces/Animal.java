package ru.progwards.java1.lessons.interfaces;

import java.util.Objects;

enum AnimalKind { ANIMAL, COW, HAMSTER, DUCK }

enum FoodKind { UNKNOWN, HAY, CORN }

public class Animal implements FoodCompare, CompareWeight {
    double weight;

    public Animal(double weight) {
        this.weight = weight;
    }
    public AnimalKind getKind() {
        return AnimalKind.ANIMAL;
    }
    public FoodKind getFoodKind() {
        return FoodKind.UNKNOWN;
    }

    @Override
    public String toString() {
        return "I am "+ getKind() +", eat "+ getFoodKind();
    }
    public double getWeight() {
        return weight;
    }
    public double getFoodCoeff() {
        return 0.02;
    }
    public double calculateFoodWeight() {
        return weight * getFoodCoeff();
    }
    public String toStringFull() {
        return toString() +" "+calculateFoodWeight();
    }

    public double getFood1kgPrice() {
        switch (getFoodKind()) {
            case HAY : return 20;
            case CORN: return 50;
            case UNKNOWN: return 0;
        }
        return 0;
    }
    public double getFoodPrice() {
        return calculateFoodWeight() * getFood1kgPrice();
    }
    @Override
    public int compareFoodPrice(Animal animal) {
        return Double.compare(this.getFoodPrice(), animal.getFoodPrice());
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return null;
    }

    @Override
    public CompareResult compareWeight(Food food) {
        return null;
    }

    @Override
    public CompareResult compareWeight(Animal animal) {
        if (weight < animal.weight) return CompareResult.LESS;
        else if (weight == animal.weight) return CompareResult.EQUAL;
        else return CompareResult.GREATER;
    }

    public static void main(String[] args) {
        Animal an = new Animal(55.0);
        Cow cow = new Cow(100.0);
        Hamster ham = new Hamster(100.0);
        Duck duck = new Duck(5.0);
        System.out.println(an.toStringFull());
        System.out.println(cow.toStringFull());
        System.out.println(ham.toStringFull());
        System.out.println(duck.toStringFull());
        System.out.println(ham.equals(ham));
        System.out.println(cow.compareWeight(duck));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
