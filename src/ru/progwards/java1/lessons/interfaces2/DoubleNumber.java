package ru.progwards.java1.lessons.interfaces2;

public class DoubleNumber extends Number {
    double num;

    public DoubleNumber(double num) {
        this.num = num;
    }

    @Override
    public Number mul(Number num) {
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

    @Override
    public int compareTo(Number o) {
        return this.toString().compareTo(o.toString());
    }
}
