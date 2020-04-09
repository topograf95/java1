package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    byte num;
    public Binary(byte num) {
        this.num = num;
    }
    @Override
    public String toString() {
        String str = "";
        int m = 8;
        while (m-- > 0) {
            str = (num & 1) + str;
            num >>= 1;
        }
        return str;
    }

    public static void main(String[] args) {
        Binary bin = new Binary((byte)-1);
        System.out.println(bin.toString());
    }
}
