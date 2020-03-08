package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {
    static final double PI = 3.14;

    public static double volumeBallDouble(double radius) {
        return 4.0 / 3.0 * PI * Math.pow(radius, 3);
    }
    public static float volumeBallFloat(float radius) {
        return (float)(4.0 / 3.0 * PI * Math.pow(radius, 3));
    }
    public static double calculateAccuracy(double radius) {
        return volumeBallDouble(radius) - volumeBallFloat((float)radius);
    }
    public static void main(String[] args) {
        System.out.println(calculateAccuracy(6371.2));
    }
}
