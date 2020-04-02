package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
    private boolean[] sieve;

    public Eratosthenes(int N) {
        sieve = new boolean[N + 1];
        Arrays.fill(sieve, true);
        sift();
    }
    private void sift() {
        for (int i = 2, n = sieve.length; i*i < n; ++i) {
            for (int j = i; i*j < n; ++j) {
                sieve[i*j] = false;
            }
        }
        sieve[0] = false;
        sieve[1] = false;
    }
//    private void sift() {
//        for (int i = 2, n = sieve.length/2 + 1; i < n; ++i)
//            for (int j = i; j < sieve.length; ++j)
//                if (j % i == 0 && j / i != 1)
//                    sieve[j] = false;
//        sieve[0] = false;
//        sieve[1] = false;
//    }
    public boolean isSimple(int n) {
        return sieve[n];
    }
    public void show() {
        System.out.println(Arrays.toString(sieve));
    }

    public static void main(String[] args) {
        Eratosthenes a = new Eratosthenes(100);
        a.show();
        System.out.println(a.isSimple(97));
    }
}
