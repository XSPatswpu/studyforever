package com.cherry.basics;

public class SyncTest {

    public synchronized void test1() {

        System.out.println("test1");
    }

    public synchronized void test2() {
        System.out.println("test2");
    }

    public void test3() {
        test1();
        test2();
    }
}
