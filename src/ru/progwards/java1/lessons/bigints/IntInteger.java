package ru.progwards.java1.lessons.bigints;

public class IntInteger extends AbsInteger {
    int aInt;

    IntInteger (int aInt) {
        this.aInt = aInt;
    }

    @Override
    public String toString() {
        return Integer.toString(aInt);
    }

    @Override
    public int add(int n1, int n2) {
        return n1 + n2;
    }

    @Override
    public byte add(byte n1, byte n2) {
        return 0;
    }

    @Override
    public short add(short n1, short n2) {
        return 0;
    }
}
