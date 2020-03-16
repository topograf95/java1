package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {
    public static boolean containsDigit(int number, int digit) {
        if (number == 0 && digit == 0) return true;
        while (number != 0) {
            if (number % 10 == digit) return true;
            number /= 10;
        }
        return false;
    }
    public static int fiboNumber(int n) {
        int a = 0, b = 0, fibo = 1;
        for (int i = 1; i < n; ++i) {
            b = fibo;
            fibo += a;
            a = b;
        }
        return fibo;
    }
    public static boolean isGoldenTriangle(int a, int b, int c) {
     //   System.out.println((double)a/c);
        if (a == b && (double)a/c > 1.61703 && (double)a/c < 1.61903) return true;
        if (a == c && (double)a/b > 1.61703 && (double)a/b < 1.61903) return true;
        if (b == c && (double)b/a > 1.61703 && (double)b/a < 1.61903) return true;
        return false;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 16; ++i)
            System.out.print(fiboNumber(i) + " ");
        System.out.print("\n");
        for (int i = 1; i < 101; ++i)
            for (int j = 1; j < 101; ++j)
                for (int k = 1; k < 101; ++k)
                    if (isGoldenTriangle(i, j, k)) System.out.println(i+" "+j+" "+k);
    }
}
