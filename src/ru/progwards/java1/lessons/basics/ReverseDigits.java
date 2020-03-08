package ru.progwards.java1.lessons.basics;

public class ReverseDigits {
    public static int reverseDigits(int number) {
        StringBuffer sb = new StringBuffer(Integer.toString(number));
        String str_tmp = new String(sb.reverse());
        return Integer.parseInt(str_tmp);
    }

    public static int reverseDigits_2(int number) {  // Только для трехзначного числа;
        int a, b, c;
        a = number % 10;
        number /= 10;
        b = number % 10;
        number /= 10;
        c = number % 10;
        return a * 100 + b * 10 + c;
    }

    public static void main(String[] args) {
        System.out.println(reverseDigits(123));
    }
}
