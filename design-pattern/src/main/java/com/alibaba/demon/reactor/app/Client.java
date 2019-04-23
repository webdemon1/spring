package com.alibaba.demon.reactor.app;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Demon
 * @create: 2019-04-23
 **/
@Slf4j
public class Client {

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        new Client().start();
    }

    private void start() {
        log.info("-- Client start --");
        executorService.execute(new TcpClient(6666, "Client-1"));
    }

    class TcpClient implements Runnable {

        private final int port;

        private final String clientName;

        private TcpClient(int port, String clientName) {
            this.port = port;
            this.clientName = clientName;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket("localhost", port);
                OutputStream ops = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(ops);
                sendLoggingRequest(socket.getInputStream(), pw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendLoggingRequest(InputStream ips, PrintWriter pw) throws IOException {
            for (int i = 0; i < 4; i++) {
                pw.println(clientName + "- log request - " + i);
                pw.flush();

                byte[] bytes = new byte[1024];
                int read = ips.read(bytes, 0, bytes.length);
                if (read != 0) {
                    log.info("{}", new String(bytes, 0, bytes.length));
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
