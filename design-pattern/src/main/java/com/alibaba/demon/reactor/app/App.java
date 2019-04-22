package com.alibaba.demon.reactor.app;

import com.alibaba.demon.reactor.framework.*;

import javax.swing.*;
import java.io.IOException;

/**
 * * This application demonstrates Reactor pattern. The example demonstrated is a Distributed Logging
 * Service where it listens on multiple TCP or UDP sockets for incoming log requests.
 * <p>
 * <p>
 * <i>INTENT</i> <br>
 * The Reactor design pattern handles service requests that are delivered concurrently to an
 * application by one or more clients. The application can register specific handlers for processing
 * which are called by reactor on specific events.
 * <p>
 * <p>
 * <i>PROBLEM</i> <br>
 * Server applications in a distributed system must handle multiple clients that send them service
 * requests. Following forces need to be resolved:
 * <ul>
 * <li>Availability</li>
 * <li>Efficiency</li>
 * <li>Programming Simplicity</li>
 * <li>Adaptability</li>
 * </ul>
 * <p>
 * <p>
 * <i>PARTICIPANTS</i> <br>
 * <ul>
 * <li>Synchronous Event De-multiplexer
 * <p>
 * {@link NioReactor} plays the role of synchronous event de-multiplexer.
 * It waits for events on multiple channels registered to it in an event loop.
 * </p>
 * </li>
 * <li>Initiation Dispatcher
 * <p>
 * {@link NioReactor} plays this role as the application specific {@link ChannelHandler}s
 * are registered to the reactor.
 * </p>
 * </li>
 * <li>Handle
 * <p>
 * {@link AbstractNioChannel} acts as a handle that is registered to the reactor.
 * When any events occur on a handle, reactor calls the appropriate handler.
 * </p>
 * </li>
 * <li>Event Handler
 * <p>
 * {@link ChannelHandler} acts as an event handler, which is bound to a
 * channel and is called back when any event occurs on any of its associated handles. Application
 * logic resides in event handlers.
 * </p>
 * </li>
 * </ul>
 * The application utilizes single thread to listen for requests on all ports. It does not create a
 * separate thread for each client, which provides better scalability under load (number of clients
 * increase).
 * The example uses Java NIO framework to implement the Reactor.
 *
 * @author: Demon
 * @create: 2019-04-21
 **/
public class App {

    private NioReactor nioReactor;

    private Dispatcher dispatcher;

    public App(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static void main(String[] args) throws IOException {
        new App(new ThreadPoolDispatcher(2)).start();
    }

    private void start() throws IOException {
        nioReactor = new NioReactor(dispatcher);
        LoggingHandler loggingHandler = new LoggingHandler();
        nioReactor.registerChannel(tcpChannel(6666, loggingHandler)).start();
    }

    private AbstractNioChannel tcpChannel(int port, ChannelHandler handler) throws IOException {
        NioServerSocketChannel channel = new NioServerSocketChannel(handler, port);
        channel.bind();
        return channel;
    }


}
