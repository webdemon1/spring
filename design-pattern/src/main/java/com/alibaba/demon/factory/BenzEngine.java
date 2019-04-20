package com.alibaba.demon.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-04-20
 **/
@Slf4j
public class BenzEngine implements Engine {

    @Override
    public void run() {
        log.info("--- BenzEngine run ---");
    }
}
