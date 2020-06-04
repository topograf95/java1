package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;

public class OrderQueue {
    static int countNum;
    PriorityQueue<Order> class1;
    PriorityQueue<Order> class2;
    PriorityQueue<Order> class3;

    OrderQueue() {
        class1 = new PriorityQueue<>();
        class2 = new PriorityQueue<>();
        class3 = new PriorityQueue<>();
    }

    public void add(Order order) {
        if (order.getSum() < 10000) class3.offer(order);
        else if (order.getSum() > 20000) class1.offer(order);
        else class2.offer(order);
    }
    public Order get() {
        if (!class1.isEmpty()) return class1.poll();
        if (!class2.isEmpty()) return class2.poll();
        if (!class3.isEmpty()) return class3.poll();
        return null;
    }

    public static void main(String[] args) {
        Order z1 = new Order(300);
        Order z2 = new Order(10500);
        Order z3 = new Order(20500);
        Order z4 = new Order(100);
        Order z5 = new Order(15000);
        OrderQueue oq = new OrderQueue();
        ArrayDeque<Order> dequeOrder = new ArrayDeque<>();
        Collections.addAll(dequeOrder, z1, z2, z3, z4, z5);
        while (!dequeOrder.isEmpty()) {
            oq.add(dequeOrder.poll());
        }
        int n = 5;
        while (n-- != 0) {
            System.out.println(oq.get().toString());
        }
    }
}
