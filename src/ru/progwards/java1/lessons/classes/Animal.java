package ru.progwards.java1.lessons.classes;

enum AnimalKind { ANIMAL, COW, HAMSTER, DUCK }

enum FoodKind { UNKNOWN, HAY, CORN }

public class Animal {
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

    public static void main(String[] args) {
        Animal an = new Animal(55.0);
        Cow cow = new Cow(100.0);
        Hamster ham = new Hamster(75.0);
        Duck duck = new Duck(5.0);
        System.out.println(an.toStringFull());
        System.out.println(cow.toStringFull());
        System.out.println(ham.toStringFull());
        System.out.println(duck.toStringFull());
    }
}
