package com.alibaba.demon.reactor.framework;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
public abstract class AbstractNioChannel {

    private final SelectableChannel channel;
    private final ChannelHandler handler;
    private NioReactor reactor;

    public AbstractNioChannel(SelectableChannel channel, ChannelHandler handler) {
        this.channel = channel;
        this.handler = handler;
    }

    public abstract Object read(SelectionKey key) throws IOException;

    public abstract void flush(SelectionKey key) throws IOException;

    public abstract int getInterestedOps();

    public SelectableChannel getJavaChannel() {
        return channel;
    }

    public void setReactor(NioReactor reactor) {
        this.reactor = reactor;
    }

}
