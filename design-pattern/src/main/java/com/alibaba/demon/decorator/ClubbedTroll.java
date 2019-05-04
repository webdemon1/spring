package com.alibaba.demon.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-05-04
 **/
@Slf4j
public class ClubbedTroll implements Troll {

    private Troll decorated;

    public ClubbedTroll(Troll troll) {
        this.decorated = troll;
    }

    @Override
    public void attack() {
        decorated.attack();
        log.info("The troll swings at you with a club!");

    }

    @Override
    public int getAttackPower() {
        return decorated.getAttackPower() + 10;
    }

    @Override
    public void fleeBattle() {
        decorated.fleeBattle();
    }
}
