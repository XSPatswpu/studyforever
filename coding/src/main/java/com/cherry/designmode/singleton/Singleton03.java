package com.cherry.designmode.singleton;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/20 15:47
 */
public class Singleton03 {
    private Singleton03() {

    }

    /**
     * 加 volatile 的原因是为了防止 new Singleton03() 操作的指令重排序
     */
    private static volatile Singleton03 instance;

    public static Singleton03 getInstance() {
        if (instance == null) {
            synchronized (Singleton03.class) {
                if (instance == null) {
                    instance = new Singleton03();
                }
            }
        }
        return instance;
    }

}
