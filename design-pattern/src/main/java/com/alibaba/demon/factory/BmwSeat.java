package com.alibaba.demon.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-04-20
 **/
@Slf4j
public class BmwSeat implements Seat {

    @Override
    public void seatDown() {
        log.info("--- seat at BMW seat---");

    }
}
