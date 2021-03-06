package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {

    public static class CacheInfo {
        public int n;
        public int fibo;
    }

    private static CacheInfo lastFibo;

    public static int fiboNumber(int n) {
        lastFibo = new CacheInfo();
        if (n == lastFibo.n) return lastFibo.fibo;
        else {
            int a = 0, b = 0, fibo = 1;
            for (int i = 1; i < n; ++i) {
                b = fibo;
                fibo += a;
                a = b;
            }
            lastFibo.n = n;
            lastFibo.fibo = fibo;
            return fibo;
        }
    }
    public static CacheInfo getLastFibo() {
        return lastFibo;
    }
    public static void clearLastFibo() {
//        lastFibo.n = 0;
//        lastFibo.fibo = 0;
        CalculateFibonacci.lastFibo = null;
    }

    public static void main(String[] args) {
        System.out.println(fiboNumber(3));
        System.out.println(getLastFibo());
        clearLastFibo();
        System.out.println(fiboNumber(6));
        clearLastFibo();
        System.out.println(getLastFibo());
        clearLastFibo();
        System.out.println(fiboNumber(6));
        System.out.println(getLastFibo());
        clearLastFibo();
    }
}
