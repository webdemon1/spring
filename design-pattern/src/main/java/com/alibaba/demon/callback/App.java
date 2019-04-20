package com.alibaba.demon.callback;

import lombok.extern.slf4j.Slf4j;

/**
 * 回调
 * @author: Demon
 * @create: 2019-04-20
 **/
@Slf4j
public class App {

    public static void main(String[] args) {
        new SimpleTask().executeWith(()->{
            log.info(" --- execute success, callback --- ");
        });
    }
}
