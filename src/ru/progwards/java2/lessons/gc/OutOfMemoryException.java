package ru.progwards.java2.lessons.gc;

public class OutOfMemoryException extends RuntimeException {
    public OutOfMemoryException(String message) {
        super(message);
    }
}
