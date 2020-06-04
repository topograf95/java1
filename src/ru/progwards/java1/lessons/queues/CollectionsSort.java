package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {
    static long speedMySort;
    static long speedMinSort;
    static long speedCollSort;

    public static void mySort(Collection<Integer> data) {
        long start = System.currentTimeMillis();
        Integer[] a = data.toArray(new Integer[data.size()]);
        int tmp ;
        for (int i = 0, n = a.length; i < n-1; ++i) {
            for (int j = i+1; j < n; ++j) {
                if (a[i] > a[j]) {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
        data.clear();
        Collections.addAll(data, a);
        speedMySort = System.currentTimeMillis() - start;
//        System.out.println(speedMySort);
    }
    public static void minSort(Collection<Integer> data) {
        long start = System.currentTimeMillis();
        int n = data.size(), elem;
        ArrayDeque<Integer> dataNew = new ArrayDeque<>(n);
        for (int i = 0; i < n; ++i) {
            elem = Collections.min(data);
            dataNew.offer(elem);
            data.remove(elem);
        }
        data.addAll(dataNew);
        speedMinSort = System.currentTimeMillis() - start;
//        System.out.println(speedMinSort);
    }
    public static void collSort(Collection<Integer> data) {
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>(data);
        Collections.sort(list);
        data.clear();
        data.addAll(list);
        speedCollSort = System.currentTimeMillis() - start;
//        System.out.println(speedCollSort);
    }
    public static Collection<String> compareSort() {
        PriorityQueue<PriorityString> priQueue = new PriorityQueue<>(3);
        priQueue.offer( new PriorityString( "mySort", speedMySort));
        priQueue.offer( new PriorityString( "minSort", speedMinSort));
        priQueue.offer( new PriorityString( "collSort", speedCollSort));
        ArrayDeque<String> nameMethod = new ArrayDeque<>(3);
        while (!priQueue.isEmpty()) {
            nameMethod.add(priQueue.poll().str);
        }
        return nameMethod;
    }

    static class PriorityString implements Comparable<PriorityString> {
        private String str;
        private long priority;
        public PriorityString(String str, long priority) {
            this.str = str;
            this.priority = priority;
        }
        @Override
        public int compareTo(PriorityString o) {
            if (priority == o.priority) return str.compareTo(o.str);
            return Long.compare(priority, o.priority);
        }
    }

    public static void main(String[] args) {
        final int ELEM_COUNT = 5000;
        ArrayList<Integer> data = new ArrayList<>(ELEM_COUNT);
        for (int i = ELEM_COUNT; i > 0; --i) data.add(i);
        mySort(data);
        Collections.reverse(data);
        minSort(data);
        Collections.reverse(data);
        collSort(data);
        System.out.println(data.toString());
        System.out.println(compareSort().toString());
    }
}
