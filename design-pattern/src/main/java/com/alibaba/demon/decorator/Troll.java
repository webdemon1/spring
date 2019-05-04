package com.alibaba.demon.decorator;

/**
 *  The Decorator pattern is a more flexible alternative to subclassing. The Decorator class
 * implements the same interface as the target and uses aggregation to "decorate" calls to the
 * target. Using the Decorator pattern it is possible to change the behavior of the class during
 * runtime.
 *
 * @author: Demon
 * @create: 2019-05-04
 **/
public interface Troll {

    void attack();

    int getAttackPower();

    void fleeBattle();

}
