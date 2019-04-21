package com.alibaba.demon.reactor.framework;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
public class NioServerSocketChannel extends AbstractNioChannel {

    private final int port;

    public NioServerSocketChannel(ChannelHandler handler, int port) throws IOException {
        super(ServerSocketChannel.open(), handler);
        this.port = port;
    }

    @Override
    public Object read(SelectionKey key) throws IOException {
        return null;
    }

    @Override
    public void flush(SelectionKey key) throws IOException {

    }

    @Override
    public int getInterestedOps() {
        return 0;
    }
}
