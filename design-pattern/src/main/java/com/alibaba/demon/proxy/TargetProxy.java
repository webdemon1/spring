package com.alibaba.demon.proxy;

import com.alibaba.demon.callback.Task;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
@Slf4j
public class TargetProxy {

    private Target target;

    public TargetProxy(Target target) {
        this.target = target;
    }

    public void invoke() {
        log.info("----TargetProxy invoke----");
        target.invoke();
    }
}
