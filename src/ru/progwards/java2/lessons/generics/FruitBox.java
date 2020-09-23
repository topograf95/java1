package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;

 class FruitBox<T extends Fruit> extends ArrayList<T> {
    ArrayList<T> box = new ArrayList<>();

    @Override
    public boolean add(T elem) {
        if (!this.box.isEmpty()) {
            if (!box.get(0).getClass().equals(elem.getClass()))
                return false;
        }
        return box.add(elem);
    }

    float getWeight() {
        if (box.size() == 0) return 0;
        Fruit elem = /*(Fruit)*/ this.box.get(0);
        return elem.getWeight() * box.size();
    }

    void moveTo(FruitBox<T> fruitBox) {
            if (!this.box.isEmpty() && !fruitBox.box.isEmpty()) {
                if (!box.get(0).getClass().equals(fruitBox.box.get(0).getClass()))
                    throw new UnsupportedOperationException();
            }
            fruitBox.box = new ArrayList<>(this.box);
            this.box.clear();
    }

    int сompareTo(FruitBox o) {
        return Double.compare(this.getWeight(), o.getWeight());
    }

    public static void main(String[] args) {
        FruitBox<Orange> boxOrange1 = new FruitBox<>();
        FruitBox boxOrange2 = new FruitBox<>();
        FruitBox<Apple> boxApple = new FruitBox<>();

        Apple app1 = new Apple();
        Apple app2 = new Apple();
        boxApple.add(app1);
        boxApple.add(app2);

        Orange oran1 = new Orange();
        Orange oran2 = new Orange();
        Orange oran3 = new Orange();
        boxOrange1.add(oran1);
        boxOrange1.add(oran2);
        boxOrange1.box.add(oran3);

        boxOrange2.add(oran1);
        boxOrange2.add(app1);

        System.out.println(boxApple.getWeight());
        System.out.println(boxOrange1.getWeight());
        System.out.println(boxOrange2.getWeight());

        System.out.println(boxApple.сompareTo(boxOrange1));
        System.out.println(boxOrange1.сompareTo(boxOrange2));

        boxOrange1.moveTo(boxOrange2);
        System.out.println(boxOrange2.getWeight());
        System.out.println(boxOrange1.getWeight());

 //       boxApple.moveTo(boxOrange2);
        System.out.println(boxOrange2.getWeight());
    }
}
