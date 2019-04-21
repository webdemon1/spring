package com.alibaba.demon.reactor.framework;

import com.google.common.collect.Queues;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
@Slf4j
public class NioReactor {

    private final Selector selector;
    private final Dispatcher dispatcher;

    /*
     * All the work of altering the SelectionKey operations and Selector operations are performed in the context of main
     * event loop of reactor. So when any channel needs to change its readability or writability, a new command is added
     * in the command queue and then the event loop picks up the command and executes it in next iteration.
     */
    private final ExecutorService reactorMain = Executors.newSingleThreadExecutor();
    private Queue<Runnable> pendingCommands = Queues.newConcurrentLinkedQueue();


    public NioReactor(Dispatcher dispatcher) throws IOException {
        this.dispatcher = dispatcher;
        this.selector = Selector.open();
    }

    @SuppressWarnings("MagicConstant")
    public NioReactor registerChannel(AbstractNioChannel channel) throws IOException {
        SelectionKey key = channel.getJavaChannel().register(selector, channel.getInterestedOps());
        key.attach(channel);
        channel.setReactor(this);
        return this;
    }

    public void start() {
        reactorMain.execute(() -> {
            try {
                log.info("--- Reactor started, waiting for events ---");
                eventLoop();
            } catch (IOException ex) {
                log.error(" exception inn eventLoop", ex);
            }

        });
    }

    public void stop() throws InterruptedException, IOException {
        reactorMain.shutdown();
        selector.wakeup();
        reactorMain.awaitTermination(4, TimeUnit.SECONDS);
        selector.close();
        log.info("Reactor stopped");
    }

    public void eventLoop() throws IOException {
        while (true) {
            if (Thread.interrupted()) {
                break;
            }

            processPendingCommands();

            /*
             * Synchronous event de-multiplexing happens here, this is blocking call which returns when it is possible to
             * initiate non-blocking operation on any of the registered channels.
             */
            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (!key.isValid()) {
                    iterator.remove();
                    continue;
                }
                processKey(key);

            }
            keys.clear();
        }
    }

    private void processPendingCommands() {
        Iterator<Runnable> iterator = pendingCommands.iterator();
        while (iterator.hasNext()) {
            Runnable runnable = iterator.next();
            runnable.run();
            iterator.remove();
        }
    }

    private void processKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            onChannelAcceptable(key);
        } else if (key.isReadable()) {
            onChannelReadable(key);
        } else if (key.isWritable()) {
            onChannelWritable(key);
        }
    }

    private void onChannelWritable(SelectionKey key) throws IOException {
        AbstractNioChannel channel = (AbstractNioChannel) (key.attachment());
        channel.flush(key);
    }

    private void onChannelReadable(SelectionKey key) {
        try {
            Object readObject = ((AbstractNioChannel) (key.attachment())).read(key);
            dispatcherReadEvent(key, readObject);
        } catch (IOException e) {
            try {
                key.channel().close();
            } catch (IOException ex) {
                log.error("error closing channel", ex);
            }
        }
    }

    private void dispatcherReadEvent(SelectionKey key, Object readObject) {
        dispatcher.onChannelReadEvent((AbstractNioChannel) (key.attachment()), readObject, key);
    }

    private void onChannelAcceptable(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(key.attachment());
    }

    public void changeOps(SelectionKey key, int interestedOps) {
        pendingCommands.add(new ChangeKeyOpsCommand(key, interestedOps));
    }

    class ChangeKeyOpsCommand implements Runnable {

        private SelectionKey key;

        private int interestedOps;

        public ChangeKeyOpsCommand(SelectionKey key, int interestedOps) {
            this.key = key;
            this.interestedOps = interestedOps;
        }

        @Override
        public void run() {
            key.interestOps(interestedOps);
        }

        @Override
        public String toString() {
            return "ChangeKeyOpsCommand{" +
                    "key=" + key +
                    ", interestedOps=" + interestedOps +
                    '}';
        }
    }

}
