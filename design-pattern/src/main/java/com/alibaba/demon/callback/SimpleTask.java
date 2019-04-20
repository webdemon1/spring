package com.alibaba.demon.callback;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-04-20
 **/
@Slf4j
public class SimpleTask extends Task {


    @Override
    public void execute() {
        log.info("--- SimpleTask execute ---");
    }
}
