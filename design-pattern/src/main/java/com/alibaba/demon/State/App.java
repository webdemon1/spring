package com.alibaba.demon.State;

/**
 * In State pattern the container object has an internal state object that defines the current
 * behavior. The state object can be changed to alter the behavior.
 * <p>
 * This can be a cleaner way for an object to change its behavior at runtime without resorting to
 * large monolithic conditional statements and thus improves maintainability.
 * <p>
 * 状态设计模式
 *
 * @author: Demon
 * @create: 2019-04-21
 **/
public class App {

    public static void main(String[] args) {
        Panda panda = new Panda();
        panda.observe();
        panda.timePasses();
        panda.observe();
        panda.timePasses();
        panda.observe();
        panda.timePasses();
        panda.observe();
    }
}
