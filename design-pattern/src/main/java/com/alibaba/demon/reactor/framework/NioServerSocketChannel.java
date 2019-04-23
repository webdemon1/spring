package com.alibaba.demon.reactor.framework;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
@Slf4j
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
    protected void doWrite(Object pendingWrite, SelectionKey key) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer) pendingWrite;
        ((SocketChannel)(key.channel())).write(byteBuffer);
    }

    @Override
    public int getInterestedOps() {
        return SelectionKey.OP_ACCEPT;
    }

    public ServerSocketChannel getJavaChannel() {
        return (ServerSocketChannel) super.getJavaChannel();
    }

    @Override
    public void bind() throws IOException {
        getJavaChannel().socket().bind(new InetSocketAddress("localhost", port));
        getJavaChannel().configureBlocking(false);
        log.info("---Bind tcp at port:{} ---", port);
    }
}
