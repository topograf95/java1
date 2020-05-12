package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {
    static BigDecimal fastPow(BigDecimal num, int pow) {
        BigDecimal result = BigDecimal.ONE;
        while (pow != 0) {
            if ((pow % 2) == 1) {
                result = result.multiply(num);
            }
            num = num.multiply(num);
            pow /= 2;
        }
        return result;
    }
    static BigInteger fibonacci(int n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b;
        BigInteger fibo = BigInteger.ONE;
        for (int i = 1; i < n; ++i) {
            b = fibo;
            fibo = fibo.add(a);
            a = b;
        }
        return fibo;
    }

    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(21);
        System.out.println(fastPow(a, 13));
        System.out.println(fibonacci(100));
    }
}
