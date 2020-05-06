package ru.progwards.java1.lessons.interfaces;

enum CompareResult { LESS, EQUAL, GREATER }

public interface CompareWeight {
    public CompareResult compareWeight(CompareWeight smthHasWeigt);
    CompareResult compareWeight(Food food);
    CompareResult compareWeight(Animal animal);
}
