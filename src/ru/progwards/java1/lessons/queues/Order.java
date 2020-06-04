package ru.progwards.java1.lessons.queues;

public class Order extends OrderQueue implements Comparable<Order> {
    private double sum;
    private int num;

    public Order(double sum) {
        this.sum = sum;
        this.num = ++countNum;
    }
    public double getSum() {
        return sum;
    }
    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(num, o.num);
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "sum=" + sum +
//                ", num=" + num +
//                '}';
//    }
}
