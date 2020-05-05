package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {

    private static CacheInfo lastFibo;

    public static class CacheInfo {
        public int n;
        public int fibo;
    }

    public static int fiboNumber(int n) {
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
        lastFibo = null;
    }
}
