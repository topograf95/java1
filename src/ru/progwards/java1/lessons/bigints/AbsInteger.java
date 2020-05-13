package ru.progwards.java1.lessons.bigints;

public abstract class AbsInteger {

    static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        int sumNum = Integer.parseInt(num1.toString()) + Integer.parseInt(num2.toString());
        if (sumNum > -129 && sumNum < 128) return new ByteInteger((byte)sumNum);
        else if (sumNum > -32769 && sumNum < 32768) return new ShortInteger((short)sumNum);
        else return new IntInteger(sumNum);
    }

    public abstract int add(int n1, int n2);

    public abstract byte add(byte n1, byte n2);

    public abstract short add(short n1, short n2);

    public static void main(String[] args) {
        ByteInteger by = new ByteInteger((byte) 50);
        ShortInteger sh = new ShortInteger((short) 1500);
        IntInteger in = new IntInteger(250000);
        System.out.println(add(by, in));
        System.out.println(add(sh, by));
    }
}
