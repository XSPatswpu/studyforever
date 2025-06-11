package com.cherry.basics;

import java.util.concurrent.CountDownLatch;

public class ThreadTest {

    public volatile int i = 0;

    public void incr() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cdl = new CountDownLatch(2);

        ThreadTest t = new ThreadTest();

        Runnable r1 = () -> {
            for (int i = 0; i < 10000; i++) {
                t.incr();
            }
            cdl.countDown();
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 10000; i++) {
                t.incr();
            }
            cdl.countDown();
        };

        Thread t1 = new Thread(r1);
        t1.start();

        Thread t2 = new Thread(r2);
        t2.start();

        cdl.await();

        System.out.println("i: " + t.i);
        System.out.println("main end.");


    }
}
