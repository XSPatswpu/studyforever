package com.cherry.designmode.singleton;

/**
 * @author: xiangshaopeng
 * @date: 2020/10/20 15:21
 */
public class Singleton01 {
    private Singleton01() {

    }

    /**
     * 类加载过程创建单例对象，不存在并发问题
     */
    private static final Singleton01 instance = new Singleton01();

    public Singleton01 getInstance() {
        return instance;
    }

}
