package ru.progwards.java2.lessons.threads;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;

class Summator {
    final int COUNT;

    Summator(int count) {
        this.COUNT = count;
    }

    public BigInteger sum(BigInteger number) {
        if (COUNT < 1) {
            System.out.println("Error: count = "+ COUNT +" Количество потоков должно быть больше нуля!");
            return BigInteger.ZERO;
        }
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        Thread[] threads = new Thread[COUNT];
        SummaTask[] tasks = new SummaTask[COUNT];
        BigInteger startRange = BigInteger.ONE;
        BigInteger range = number.divide(BigInteger.valueOf(COUNT));
        BigInteger endRange = range;

        for (int i = 0; i < COUNT; ++i) {
            if (i + 1 == COUNT) {
                endRange = number;
            }
            tasks[i] = new SummaTask (countDownLatch, startRange, endRange);
            threads[i] = new Thread (tasks[i]);
            threads[i].start();
            startRange = endRange.add(BigInteger.ONE);
            endRange = endRange.add(range);
        }

        BigInteger totalSum = BigInteger.ZERO;
        try {
            countDownLatch.await ();
            for (int i = 0; i < COUNT; ++i) {
                totalSum = totalSum.add(tasks[i].sumt);
            }
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        return totalSum;
    }

    class SummaTask implements Runnable {
        CountDownLatch countDownLatch;
        BigInteger a;
        BigInteger b;
        BigInteger sumt;

        SummaTask(CountDownLatch countDownLatch, BigInteger a, BigInteger b) {
            this.countDownLatch = countDownLatch;
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            this.sumt = summa(a, b);
            countDownLatch.countDown();
        }

        BigInteger summa(BigInteger a, BigInteger b) {
            BigInteger summ = BigInteger.ZERO;
            while (a.compareTo(b) <= 0) {
                summ = summ.add(a);
                a = a.add(BigInteger.ONE);
            }
            return summ;
        }
    }

    public static void main(String[] args) {
        Summator su = new Summator(10);
        BigInteger number = new BigInteger("100000000");
        long start = System.currentTimeMillis();
        System.out.println("Сумма = "+ su.sum(number));
        long stop = System.currentTimeMillis();
        System.out.println("Время = "+ (stop - start));
    }
}
