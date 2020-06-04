package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackCalc {
    Deque<Double> stack;

    StackCalc() {
        stack = new ArrayDeque<>();
    }
    public void push(double value) {
        stack.push(value);
    }
    public double pop() {
        return stack.pop();
    }
    public void add() {
        push(pop() + pop());
    }
    public void sub() {
        double first = pop();
        push(pop() - first);
    }
    public void mul() {
        push(pop() * pop());
    }
    public void div() {
        double first = pop();
        push(pop() / first);
    }
}
