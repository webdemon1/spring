package com.alibaba.demon.reactor.framework;

import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

/**
 * @author: Demon
 * @create: 2019-04-22
 **/
@Slf4j
public class LoggingHandler implements ChannelHandler {

    private final byte[] ACK = "DataSent success".getBytes();

    @Override
    public void handleChannelRead(AbstractNioChannel channel, Object readObject, SelectionKey key) {

        if(readObject instanceof ByteBuffer) {
           doLogging((ByteBuffer) readObject);
           channel.write(ByteBuffer.wrap(ACK),key);
        } else if(readObject instanceof DatagramPacket) {

        }

    }

    private void doLogging(ByteBuffer readObject) {
        log.info("--LoggingHandler.doLogging data:{}",new String(readObject.array(),0,readObject.limit()));
    }
}
