package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
        int sumbit = 0;
        while (value > 0) {
            sumbit += value & 1;
            value >>= 1;
        }
        return sumbit;
    }

    public static void main(String[] args) {
        System.out.println(sumBits((byte)127));
    }
}
