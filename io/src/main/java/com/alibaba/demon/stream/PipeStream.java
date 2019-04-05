package com.alibaba.demon.stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * pipeStream 可以用于多线程之间通信
 * @author: Demon
 * @create: 2019-04-05
 **/
public class PipeStream {

    private static final PipedInputStream pis = new PipedInputStream(102400);

    private static final PipedOutputStream pos = new PipedOutputStream();

    public static void main(String[] args) throws IOException, InterruptedException {
        pis.connect(pos);

        Thread writeThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 200; i++) {
                    pos.write(i);
                }
                pos.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Thread readThread = new Thread(() -> {
            try {
                Thread.sleep(120);
                byte[] bytes = new byte[1];
                while ((pis.read(bytes)) >= 0) {
                    System.out.println(bytes[0] & 0xFF);
                }
                pis.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        writeThread.start();
        readThread.start();

        writeThread.join();
        readThread.join();

        System.exit(0);
    }

}
