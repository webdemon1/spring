package com.alibaba.demon.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-05-04
 **/
@Slf4j
public class SimpleTroll implements Troll {

    @Override
    public void attack() {
        log.info("the troll try to grab you!");
    }

    @Override
    public int getAttackPower() {
        return 10;
    }

    @Override
    public void fleeBattle() {
        log.info("the troll run away");
    }
}
