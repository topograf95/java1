package ru.progwards.java2.lessons.recursion;

public class AsNumbersSum {
    public static String asNumbersSum(int number) {
        return numbersSum(number, number,"");
    }

    private static String numbersSum(int usedNum, int sum, String prefix) {
        if (sum == 0) return prefix;
        int d;
        if (usedNum < sum) {
            d = usedNum;
        } else d = sum;
        String s = numbersSum(d,sum - d, (prefix.isBlank())? ""+ d : prefix +"+"+ d);
        if (d > 1) {
            s = s +" = "+ numbersSum(d - 1, sum, prefix);
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(asNumbersSum(1));
        System.out.println(asNumbersSum(2));
        System.out.println(asNumbersSum(3));
        System.out.println(asNumbersSum(4));
        System.out.println(asNumbersSum(5));
        System.out.println(asNumbersSum(6));
        System.out.println(asNumbersSum(7));
    }
}
