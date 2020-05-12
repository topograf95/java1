package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {
    byte[] digits;
    int lenDigit;

    ArrayInteger(int n) {
        this.digits = new byte[n];
    }

    void fromInt(BigInteger value) {
        int i = 0;
        while (!value.equals(BigInteger.ZERO)) {
            digits[i] = Byte.parseByte(String.valueOf(value.remainder(BigInteger.TEN)));
            value = value.divide(BigInteger.TEN);
            ++i;
        }
        lenDigit = i;
    }

    BigInteger toInt() {
        String digStr = "";
        for (int i = lenDigit-1; i >= 0; --i) {
            digStr += digits[i];
        }
        return new BigInteger(digStr);
    }
    boolean add(ArrayInteger num) {
        int n1 = digits.length;
        int n2 = num.digits.length;
        if (n1 < n2) { this.zeroing(); return false;}
        byte[] tmp = new byte[n1];
        System. arraycopy(num.digits, 0, tmp, 0, n2);
        if (digits[n1-1] + tmp[n1-1] > 9) { this.zeroing(); return false;}
        byte[] result = new byte[n1+1];
        for (int i = 0; i < n1; ++i) {
            result[i] += (byte) (this.digits[i] + tmp[i]);
            if (result[i] > 9) {
                result[i] %= 10;
                result[i+1] = 1;
            }
        }
        if (result[n1] == 1) { this.zeroing(); return false;}
        System. arraycopy(result, 0, this.digits, 0, n1);
        for ( int i = n1-1; i >= 0; --i) {
            if (digits[i] != 0) { n2 = i + 1; break;}
        }
        this.lenDigit = n2;
        return true;
    }

    void zeroing() {
            this.digits = new byte[1];
            this.lenDigit = 1;
        }
//        void show() {
//        System.out.println(Arrays.toString(digits));
//    }
//
//    public static void main(String[] args) {
//        ArrayInteger ar = new ArrayInteger(5);
//        ArrayInteger b = new ArrayInteger(4);
//        ar.fromInt(new BigInteger(String.valueOf(9999)));
//        b.fromInt(new BigInteger(String.valueOf(11)));
//        ar.show();
//        System.out.println(b.toInt());
//        System.out.println(ar.add(b));
//        ar.show();
//        System.out.println(ar.toInt());
//    }
}
