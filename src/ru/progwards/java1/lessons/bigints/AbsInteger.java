package ru.progwards.java1.lessons.bigints;

public abstract class AbsInteger {

    static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        return null;
    }

    public abstract int add(int n1, int n2);

    public abstract byte add(byte n1, byte n2);

    public abstract short add(short n1, short n2);

    public static void main(String[] args) {
        ByteInteger by = new ByteInteger((byte) 5);
        ShortInteger sh = new ShortInteger((short) 15);
        IntInteger in = new IntInteger(25);
        System.out.println(add(by, in));
    }
}
