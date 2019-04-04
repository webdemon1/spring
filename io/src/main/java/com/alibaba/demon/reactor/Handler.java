package com.alibaba.demon.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author: Demon
 * @create: 2019-04-04
 **/
@Slf4j
public class Handler implements Runnable {

    final SocketChannel socket;

    final SelectionKey sk;

    ByteBuffer input = ByteBuffer.allocate(9600);

    public Handler(Selector selector, SocketChannel c) throws IOException {
        socket = c;
        c.configureBlocking(false);
        sk = socket.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
        log.info("---- Reactor init ----");
    }

    @Override
    public void run() {
        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read() throws IOException {
        while (socket.read(input) != -1) {
            input.flip();
            int anInt = input.getInt();
            System.out.println(anInt);
        }
    }
}
