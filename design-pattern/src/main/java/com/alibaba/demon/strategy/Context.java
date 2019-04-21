package com.alibaba.demon.strategy;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 调用策略
     */
    public void contextInterface() {
        strategy.algorithmLogic();
    }

}
