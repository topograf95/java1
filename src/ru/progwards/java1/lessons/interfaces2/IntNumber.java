package ru.progwards.java1.lessons.interfaces2;

public class IntNumber extends Number {
    int num;

    public IntNumber(int num) {
        this.num = num;
    }

    @Override
    public Number mul(Number num) {
        return new IntNumber(this.num * ((IntNumber)num).num);
    }

    @Override
    public Number div(Number num) {
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

    @Override
    public int compareTo(Number o) {
        return CharSequence.compare(this.toString(), o.toString());
    }

//    @Override
//    public int compareTo(Number o) {
//        return Integer.compare(Integer.parseInt(this.toString()),
//                Integer.parseInt(o.toString()));
//    }
}