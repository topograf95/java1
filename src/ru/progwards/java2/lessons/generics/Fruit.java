package ru.progwards.java2.lessons.generics;

public class Fruit {
    public float getWeight() {
        return 0;
    }
}

class Apple extends Fruit {
    float weight = 1.0f;

    @Override
    public float getWeight() {
        return weight;
    }
}

class Orange extends Fruit {
    float weight = 1.5f;

    @Override
    public float getWeight() {
        return weight;
    }
}
