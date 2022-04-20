package com.cherry.basics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SlideWindowLimit {

    /**
     * 限流个数
     */
    private Long limitCount;

    /**
     * 小窗口数组
     */
    private SubWindow[] windowArray;

    /**
     * 小窗口个数
     */
    private Integer windowSize = 10;

    /**
     * 窗口总时长
     */
    private Long windowTime;

    public SlideWindowLimit(Long limitCount, Long timeUnit) {
        this.limitCount = limitCount;
        this.windowTime = timeUnit;

        windowArray = new SubWindow[windowSize];
        long current = System.currentTimeMillis();
        long millis = current % windowTime;
        long startTime = current - millis;

        for (int i = 0; i < windowArray.length; i++) {
            windowArray[i] = new SubWindow(0L, startTime + i * (windowTime / windowSize));
        }
    }

    // 方法

    public synchronized boolean tryRequire() {
        long current = System.currentTimeMillis();
        long millis = current % windowTime;
        long startTime = current - millis;

        int index = (int) ((current % windowTime) / (windowTime / windowSize));

        int sum = 0;
        for (int i = 0; i < windowArray.length; i++) {
            if (isWindowExpire(windowArray[i], current)){
                // reset
                windowArray[i] = new SubWindow(0L, startTime + i * (windowTime / windowSize));
            }
            sum += windowArray[i].getCount();
        }

        if (sum < limitCount) {
            Long count = windowArray[index].getCount();
            windowArray[index].setCount(count + 1);
            return true;
        }

        return false;
    }

    private boolean isWindowExpire(SubWindow subWindow, Long current) {
        return current - subWindow.getStartTime() > windowTime;
    }

    private static class SubWindow {
        private Long count;

        private Long startTime;

        public SubWindow(Long count, Long startTime) {
            this.count = count;
            this.startTime = startTime;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }

        public Long getStartTime() {
            return startTime;
        }

        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SlideWindowLimit slideWindowLimit = new SlideWindowLimit(10L, 1000L);
        Thread.sleep(500);
        while (true) {
            if (slideWindowLimit.tryRequire()) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
                System.out.println("do something ... " + df.format(new Date()));
            } else {
                System.out.println("be limit ...");
            }
            Thread.sleep(20);
        }
    }
}
