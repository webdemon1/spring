package com.alibaba.demon.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author: Demon
 * @create: 2019-04-04
 **/
@Slf4j
public class Acceptor implements Runnable {

    private ServerSocketChannel serverSocket;
    private Selector selector;

    public Acceptor(ServerSocketChannel socket, Selector selectorIn) {
        this.serverSocket = socket;
        this.selector = selectorIn;
    }

    @Override
    public void run() {
        try {
            SocketChannel c = serverSocket.accept();
            if (c != null) {
                log.info("---- accept a connection ----");
                new Handler(selector, c);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
