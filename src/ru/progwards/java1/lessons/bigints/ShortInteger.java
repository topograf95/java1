package ru.progwards.java1.lessons.bigints;

public class ShortInteger extends AbsInteger {
    short aShort;

    ShortInteger (short aShort) {
        this.aShort = aShort;
    }
    @Override
    public String toString() {
        return Short.toString(aShort);
    }

    @Override
    public int add(int n1, int n2) {
        return 0;
    }

    @Override
    public byte add(byte n1, byte n2) {
        return 0;
    }

    @Override
    public short add(short n1, short n2) {
        return (short) (n1 + n2);
    }
}
