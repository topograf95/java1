package ru.progwards.java1.lessons.bigints;

public class ByteInteger extends AbsInteger {
    byte aByte;

    ByteInteger (byte aByte) {
        this.aByte = aByte;
    }

    @Override
    public String toString() {
        return Byte.toString(aByte);
    }

    @Override
    public int add(int n1, int n2) {
        return 0;
    }

    @Override
    public byte add(byte n1, byte n2) {
        return (byte) (n1 + n2);
    }

    @Override
    public short add(short n1, short n2) {
        return 0;
    }
}
