package com.alibaba.demon.singleton;

/**
 * 枚举中一个实例，就是天然的单例
 *
 * @author: Demon
 * @create: 2019-04-20
 **/
public enum EnumBasedSingleton {

    INSTANCE;

    @Override
    public String toString() {
        return getDeclaringClass().getCanonicalName() + "@" + hashCode();
    }
}
