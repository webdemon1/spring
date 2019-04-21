package com.alibaba.demon.State;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
@Slf4j
public class AngryState implements State {

    private Panda panda;

    public AngryState(Panda panda) {
        this.panda = panda;
    }

    @Override
    public void onEnterState() {
        log.info("-- panda get angry --");
    }

    @Override
    public void observe() {
        log.info("-- panda is angry --");
    }
}
