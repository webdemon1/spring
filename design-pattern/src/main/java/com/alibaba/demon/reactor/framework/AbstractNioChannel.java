package com.alibaba.demon.reactor.framework;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
public abstract class AbstractNioChannel {

    private final SelectableChannel channel;
    private final ChannelHandler handler;
    private NioReactor reactor;
    private final Map<SelectableChannel, Queue<Object>> channel2PendingWrites = Maps.newConcurrentMap();

    public AbstractNioChannel(SelectableChannel channel, ChannelHandler handler) {
        this.channel = channel;
        this.handler = handler;
    }

    // write data to channel
    protected abstract void doWrite(Object pendingWrite, SelectionKey key) throws IOException;

    public abstract int getInterestedOps();

    public abstract void bind() throws IOException;

    public abstract Object read(SelectionKey key) throws IOException;

    public void flush(SelectionKey key) throws IOException {
        Queue<Object> pendingWrites = channel2PendingWrites.get(key.channel());
        while (true) {
            Object pendingWrite = pendingWrites.poll();
            if (Objects.isNull(pendingWrite)) {
                reactor.changeOps(key, SelectionKey.OP_READ);
                break;
            }
            doWrite(pendingWrite, key);
        }
    }

    public void write(Object data, SelectionKey key) {
        Queue<Object> queue = channel2PendingWrites.get(key.channel());
        if (Objects.isNull(queue)) {
            synchronized (this.channel2PendingWrites) {
                queue = channel2PendingWrites.get(key.channel());
                if (Objects.isNull(queue)) {
                    queue = new ConcurrentLinkedDeque<>();
                    channel2PendingWrites.put(key.channel(), queue);
                }
            }
        }
        queue.add(data);
        reactor.changeOps(key, SelectionKey.OP_READ);
    }


    public SelectableChannel getJavaChannel() {
        return channel;
    }

    public void setReactor(NioReactor reactor) {
        this.reactor = reactor;
    }

}
