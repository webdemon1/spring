package com.alibaba.demon.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-04-20
 **/
@Slf4j
public class PlasticToyDuck implements ToyDuck {
    @Override
    public void squeak() {
        log.info("--PlasticToyDuck 吱吱吱 --");
    }
}
