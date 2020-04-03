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
        for (int p = 2, n = sieve.length; p*p < n; ++p) {
            if (sieve[p] == false) continue;   // Пропускаются зачеркнутые числа,
            for (int j = p; p*j < n; ++j) {    // цикл начинается с первого не зачеркнутого числа j = p;
                sieve[p*j] = false;
            }
        }
        sieve[0] = false;
        sieve[1] = false;
    }
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
