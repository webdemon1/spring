package com.alibaba.demon.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 麻雀
 * @author: Demon
 * @create: 2019-04-20
 **/
@Slf4j
public class Sparrow implements Bird {

    @Override
    public void fly() {
        log.info("--Sparrow flying--");
    }

    @Override
    public void makeSound() {
        log.info("--Sparrow make sound--");
    }
}
