package com.alibaba.demon.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
@Slf4j
public class TargetImpl implements Target {

    @Override
    public void invoke() {
        log.info("----TargetImpl invoke----");
    }
}
