package com.alibaba.demon.reactor.framework;

import java.nio.channels.SelectionKey;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
public class ThreadPoolDispatcher implements Dispatcher {

    private final ExecutorService executorService;

    public ThreadPoolDispatcher(int n) {
        this.executorService = Executors.newFixedThreadPool(n);
    }

    @Override
    public void onChannelReadEvent(AbstractNioChannel channel, Object readObject, SelectionKey key) {
    executorService.execute(()->{
        channel.getHandler().handleChannelRead(channel,readObject,key); });
    }

    @Override
    public void stop() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}
