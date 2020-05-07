package ru.progwards.java1.lessons.interfaces;

// enum CompareResult { LESS, EQUAL, GREATER }

public interface CompareWeight {
    public enum CompareResult { LESS, EQUAL, GREATER }
    public CompareResult compareWeight(CompareWeight smthHasWeigt);
}
