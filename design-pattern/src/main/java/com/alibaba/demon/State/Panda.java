package com.alibaba.demon.State;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
public class Panda {

    private State state;

    public Panda() {
        state = new PeacefulState(this);
    }

    public void timePasses() {
        if (state.getClass().equals(PeacefulState.class)) {
            changeStateTo(new AngryState(this));
        } else {
            changeStateTo(new PeacefulState(this));
        }
    }

    void changeStateTo(State state) {
        this.state = state;
        this.state.onEnterState();
    }

    void observe() {
        this.state.observe();
    }

}
