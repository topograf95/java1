package ru.progwards.java1.lessons.abstractnum;

public class Test {
    public static void main(String[] args) {
        Cube cubInt = new Cube(new IntNumber(3));
        System.out.println(cubInt.volume());
        Cube cubDoubl = new Cube(new DoubleNumber(3.0));
        System.out.println(cubDoubl.volume());
        Pyramid pyramidInt = new Pyramid(new IntNumber(3));
        System.out.println(pyramidInt.volume());
        Pyramid pyramidDoubl = new Pyramid(new DoubleNumber(3.0));
        System.out.println(pyramidDoubl.volume());
    }
}
