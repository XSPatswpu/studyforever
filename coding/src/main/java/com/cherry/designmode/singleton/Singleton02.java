package com.cherry.designmode.singleton;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/20 15:47
 */
public class Singleton02 {
    private Singleton02() {

    }

    /**
     * 存在并发问题
     */
    private Singleton02 instance;

    public synchronized Singleton02 getInstance() {
        if (instance == null) {
            return new Singleton02();
        }
        return instance;
    }

}
