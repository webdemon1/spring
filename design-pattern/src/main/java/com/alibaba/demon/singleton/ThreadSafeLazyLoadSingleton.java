package com.alibaba.demon.singleton;

/**
 * @author: Demon
 * @create: 2019-04-20
 **/
public class ThreadSafeLazyLoadSingleton {

    private static ThreadSafeLazyLoadSingleton instance;

    private ThreadSafeLazyLoadSingleton() {
        // 防止反射创建对象
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static synchronized ThreadSafeLazyLoadSingleton getInstance() {
        return instance == null ? new ThreadSafeLazyLoadSingleton() : instance;
    }

}
