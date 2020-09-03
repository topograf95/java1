package ru.progwards.java1.lessons.abstractnum;

public class DoubleNumber extends Number {
    double num;

    public DoubleNumber(double num) {
        this.num = num;
    }

    @Override
    public Number mul(Number num) {
//        return new DoubleNumber(this.num * Double.parseDouble(num.toString()));
        return new DoubleNumber(this.num * ((DoubleNumber)num).num);
    }

    @Override
    public Number div(Number num) {
        return new DoubleNumber(this.num / Double.parseDouble(num.toString()));
    }

    @Override
    public Number newNumber(String strNum) {
        return new DoubleNumber(Double.parseDouble(strNum));
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
