package ru.progwards.java1.lessons.abstractnum;

public class IntNumber extends Number {
    int num;

    public IntNumber(int num) {
        this.num = num;
    }

    @Override
    public Number mul(Number num) {
//        return new IntNumber(this.num * Integer.parseInt(num.toString()));
        return new IntNumber(this.num * ((IntNumber)num).num);
    }

    @Override
    public Number div(Number num) {
//        return new IntNumber(this.num / Integer.parseInt(num.toString()));
        return new IntNumber(this.num / ((IntNumber)num).num);
    }

    @Override
    public Number newNumber(String strNum) {
        return new IntNumber(Integer.parseInt(strNum));
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
