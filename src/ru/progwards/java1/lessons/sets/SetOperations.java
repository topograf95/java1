package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet(set1);
        result.addAll(set2);
        return result;
    }
    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet(set1);
        result.retainAll(set2);
        return result;
    }
    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet(set1);
        result.removeAll(set2);
        return result;
    }
    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result1 = new HashSet(set1);
        Set<Integer> result2 = new HashSet(set2);
        result1.removeAll(set2);
        result2.removeAll(set1);
        Set<Integer> result = new HashSet(result1);
        result.addAll(result2);
        return result;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = Set.of(1, 2, 3, 4, 5, 6, 7);
        Set<Integer> set2 = Set.of(4, 5, 6, 7, 8, 9, 10);
        System.out.println(union(set1, set2));
        System.out.println(intersection(set1, set2));
        System.out.println(difference(set1, set2));
        System.out.println(symDifference(set1, set2));
    }
}
