package com.cherry.testcode.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FutureTest {

    /*
    结论：Future get 方法超市不会中断异步线程，只会抛出 timeoutException
     */
    public static void main(String[] args) {

        // submit task
        CompletableFuture<Integer> c = CompletableFuture.supplyAsync(() -> {

            System.out.println("--start--");

            System.out.println("--sleep--");

            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("--end--");

            return 0;
        });

        // future.get
        try {
            Integer i = c.get(2, TimeUnit.SECONDS);
            System.out.println("--result--" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--main sleep end--");

    }
}
