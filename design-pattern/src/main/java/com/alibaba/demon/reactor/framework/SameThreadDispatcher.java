package com.alibaba.demon.reactor.framework;

import java.nio.channels.SelectionKey;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
public class SameThreadDispatcher implements Dispatcher {


    @Override
    public void onChannelReadEvent(AbstractNioChannel channel, Object readObject, SelectionKey key) {

    }
}
