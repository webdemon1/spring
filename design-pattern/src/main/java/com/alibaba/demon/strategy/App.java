package com.alibaba.demon.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 策略设计模式
 *
 * @author: Demon
 * @create: 2019-04-21
 **/
@Slf4j
public class App {

    public static void main(String[] args) {
        Context contextA = new Context(new ConcreteStrategyA());
        contextA.contextInterface();

        log.info("---------------------");

        Context contextB = new Context(new ConcreteStrategyB());
        contextB.contextInterface();
    }

    /*
     *  优点：
     * 1.对客户隐藏具体策略(算法)的实现细节，彼此完全独立。具体策略类实现自同一个接口，他们之间可以自由切换，易于扩展，
     * 如果需要添加新的策略类，基本不用改变现有代码
     * 2.使用策略模式可以避免使用多重条件转移语句，多重转移语句不易维护。
     * 它把采取哪一种算法或采取哪一种行为的逻辑与算法或行为的逻辑混合在一起，统统列在一个多重转移语句里面，比使用继承的办法还要原始和落后。
     *
     */


}
