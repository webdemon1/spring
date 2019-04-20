package com.alibaba.demon.singleton;

import static org.junit.Assert.assertEquals;

/**
 * @author: Demon
 * @create: 2019-04-20
 **/
public class App {

    public static void main(String[] args) {
        EnumBasedSingleton instance1 = EnumBasedSingleton.INSTANCE;
        EnumBasedSingleton instance2 = EnumBasedSingleton.INSTANCE;
        assertEquals(instance1, instance2);

        Singleton instance3 = Singleton.getInstance();
        Singleton instance4 = Singleton.getInstance();
        assertEquals(instance3, instance4);

        ThreadSafeLazyLoadSingleton instance5 = ThreadSafeLazyLoadSingleton.getInstance();
        ThreadSafeLazyLoadSingleton instance6 = ThreadSafeLazyLoadSingleton.getInstance();
        assertEquals(instance5, instance6);
    }
}
