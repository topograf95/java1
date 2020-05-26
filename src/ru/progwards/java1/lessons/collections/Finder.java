package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static java.util.Collections.sort;

public class Finder {
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        Integer[] arrNumbers = numbers.toArray(Integer[]::new);
        int minSum = arrNumbers[0] + arrNumbers[1], k = 0;
        for (int i = 1, n = arrNumbers.length-1; i < n; ++i) {
            if (minSum > arrNumbers[i] + arrNumbers[i+1]) {
                minSum = arrNumbers[i] + arrNumbers[i+1];
                k = i;
            }
        }
        Collection<Integer> minSumPair = new ArrayList<>(2);
        minSumPair.add(k);
        minSumPair.add(k+1);
        return minSumPair;
//        arrNumbers[k] = k;
//        arrNumbers[k+1] = k+1;
//        return Arrays.asList(arrNumbers);
    }
    public static Collection<Integer> findLocalMax(Collection<Integer> numbers) {
        Collection<Integer> localMax = new ArrayList();
        Integer[] arrNumbers = numbers.toArray(Integer[]::new);
        for (int i = 1, n = arrNumbers.length-1; i < n; ++i) {
            if (arrNumbers[i] > arrNumbers[i-1] && arrNumbers[i] > arrNumbers[i+1]) {
                localMax.add(arrNumbers[i]);
            }
        }
        return localMax;
    }
    public static boolean findSequence(Collection<Integer> numbers) {
        ArrayList<Integer> list = new ArrayList<>(numbers);
        sort(list);
        boolean chek = true;
        for (int i = 0, n = list.size(); i < n; ++i) {
            if (list.get(i) != i + 1) {
                chek = false;
                break;
            }
        }
        return chek;
    }
    public static String findSimilar(Collection<String> names) {
        String[] arrNames = names.toArray(String[]::new);
        String name = "";
        int a = 1, res = 0;
        for (int i = 0, n = arrNames.length-1; i < n; ++i) {
            if (arrNames[i].equals(arrNames[i+1])) ++a;
            if (!arrNames[i].equals(arrNames[i+1]) || (i == n-1)) {
                if (a > res) {
                    res = a;
                    name = arrNames[i];
                }
                a = 1;
            }
        }
        return name +':'+ res;
    }

    public static void main(String[] args) {
        String[] arr = {"1", "3", "3", "5", "4", "4", "4", "2", "4"};
        Collection<String> coll = new ArrayList(Arrays.asList(arr));
        System.out.println(findSimilar(coll));
    }
}
