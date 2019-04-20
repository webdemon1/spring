package com.alibaba.demon.adapter;

/**
 *
 * @author: Demon
 * @create: 2019-04-20
 **/
public class BirdAdapter implements ToyDuck {

    private Bird bird;

    public BirdAdapter(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void squeak() {
        bird.makeSound();
    }
}
