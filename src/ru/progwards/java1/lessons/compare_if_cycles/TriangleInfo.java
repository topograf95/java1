package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {
    public static boolean isTriangle(int a, int b, int c) {
        return a < b+c && b < a+c && c < a+b;
    }
    public static boolean isRightTriangle(int a, int b, int c) {
        return a*a + b*b == c*c || a*a + c*c == b*b || b*b + c*c == a*a;
    }
    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        return a == b || a == c || b == c;
    }

    public static void main(String[] args) {
        System.out.println(isTriangle(3, 2, 3));
        System.out.println(isRightTriangle(5, 3, 4));
        System.out.println(isIsoscelesTriangle(4, 5, 5));
    }
}
