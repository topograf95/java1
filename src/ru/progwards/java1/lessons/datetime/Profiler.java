package ru.progwards.java1.lessons.datetime;

import java.util.*;

public class Profiler {
    private static ArrayDeque<StatisticStart> startTime  = new ArrayDeque<>();
    private static Map<String, StatisticInfo> mapNameStInfo = new TreeMap<>();

    static class StatisticStart {
        public long startFullTime;
        public int startSelfTime;
    }

    public static void enterSection(String name) {
        long start = System.currentTimeMillis();
        StatisticStart ss = new StatisticStart();
        ss.startFullTime = start;
        startTime.offer(ss);
    }

    public static void exitSection(String name) {
        long finish = System.currentTimeMillis();
        StatisticStart ss = startTime.pollLast();
        int interval = (int) (finish - ss.startFullTime);
        StatisticInfo a = new StatisticInfo();
        a.sectionName = name;
        a.fullTime = interval;
        a.selfTime = interval + ss.startSelfTime;
        a.count = 1;
        StatisticInfo b = mapNameStInfo.putIfAbsent(name, a);
        if (b != null) {
            a.fullTime += b.fullTime;
            a.selfTime += b.selfTime;
            a.count += b.count;
            mapNameStInfo.put(name, a);
        }
        if (!startTime.isEmpty()) {
            ss = startTime.pollLast();
            ss.startSelfTime -= interval;
            startTime.offer(ss);
        }
    }

    public static List<StatisticInfo> getStatisticInfo() {
        List<StatisticInfo> si = new ArrayList<>(mapNameStInfo.values());
        return si;
    }

    public static void main(String[] args) {
        enterSection("1");
        enterSection("2");
        long x = 0;
        for (int i = 0; i < 100; ++i) {
            enterSection("3");
            for (int j = 0; j < 100000; ++j) {
                x += Math.pow(2, 3);
            }
            exitSection("3");
        }
        System.out.println(getStatisticInfo());
        exitSection("2");
        System.out.println(getStatisticInfo());
        for (int i = 0; i < 100; ++i) {
            enterSection("4");
            for (int j = 0; j < 100000; ++j) {
                x += Math.pow(2, 3);
            }
            exitSection("4");
        }
        exitSection("1");
        System.out.println(getStatisticInfo());
    }
}
