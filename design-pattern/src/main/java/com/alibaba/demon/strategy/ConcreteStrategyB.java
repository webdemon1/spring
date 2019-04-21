package com.alibaba.demon.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
@Slf4j
public class ConcreteStrategyB implements Strategy {

    @Override
    public void algorithmLogic() {
        log.info("----金牌会员6折----");

    }
}
