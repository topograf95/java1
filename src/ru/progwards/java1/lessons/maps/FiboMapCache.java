package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.*;

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache;
    private boolean cacheOn;

    public FiboMapCache(boolean cacheOn) {
        this.cacheOn = cacheOn;
        if (cacheOn) fiboCache = new HashMap<>();
    }
    public BigDecimal fiboNumber(int n) {
        BigDecimal bigFibo = null;
        if (cacheOn) bigFibo = fiboCache.get(n);
        if (bigFibo != null) return bigFibo;
        BigDecimal a = ZERO;
        BigDecimal b;
        bigFibo = ONE;
        for (int i = 1; i < n; ++i) {
            b = bigFibo;
            bigFibo = bigFibo.add(a);
            a = b;
        }
        if (cacheOn) fiboCache.put(n, bigFibo);
        return bigFibo;
    }
    public void clearCahe() {
        if (cacheOn) fiboCache.clear();
        fiboCache = null;
        cacheOn = false;
    }
    public static void test() {
        int m = 1000;
        long start = System.currentTimeMillis();
        FiboMapCache fmc = new FiboMapCache(true);
        for (int i = 1; i <= m; ++i) fmc.fiboNumber(i);
        System.out.println("fiboNumber cacheOn="+ fmc.cacheOn +" время выполнения "+
                (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        fmc = new FiboMapCache(false);
        for (int i = 1; i <= m; ++i) fmc.fiboNumber(i);
        System.out.println("fiboNumber cacheOn="+ fmc.cacheOn +" время выполнения "+
                (System.currentTimeMillis() - start));
        }

    public static void main(String[] args) {
        test();
//        FiboMapCache fmc = new FiboMapCache(true);
//        System.out.println(fmc.fiboNumber(10));
//        fmc.clearCahe();
//        System.out.println(fmc.fiboCache);
//        System.out.println(fmc.fiboNumber(10));
    }
}
