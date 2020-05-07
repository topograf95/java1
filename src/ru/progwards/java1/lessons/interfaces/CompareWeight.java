package ru.progwards.java1.lessons.interfaces;

enum CompareResult { LESS, EQUAL, GREATER }

public interface CompareWeight {
    public CompareResult compareWeight(CompareWeight smthHasWeigt);
}
