package com.cherry.basics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class FixWindowLimit {

    // 一定时间内的数量限制
    private Integer limitCount;

    // 时间间隔
    private Long timeUnit;

    public FixWindowLimit(Integer limitCount, Long timeUnit) {
        this.limitCount = limitCount;
        this.timeUnit = timeUnit;
        init();
    }

    // 计数器，用来记录当前窗口的请求数量
    private final AtomicInteger count = new AtomicInteger();

    // 开始时间，记录当前窗口的开始时间
    private volatile Long winStartTime;

    // 方法

    public void init() {
        winStartTime = System.currentTimeMillis();
        count.set(0);
    }

    // tryRequire
    public boolean tryRequire() {
        long current = System.currentTimeMillis();
        if (current > winStartTime + timeUnit) {
            // 重置
            count.set(0);
            winStartTime = System.currentTimeMillis();
        }
        return count.incrementAndGet() <= limitCount;
    }

    public static void main(String[] args) throws InterruptedException {

        FixWindowLimit fixWindowLimit = new FixWindowLimit(10, 1000L);
        Thread.sleep(500);
        while (true) {
            if (fixWindowLimit.tryRequire()) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
                System.out.println("do something ... " + df.format(new Date()));
            } else {
                System.out.println("be limit ...");
            }
            Thread.sleep(20);
        }
    }
}
