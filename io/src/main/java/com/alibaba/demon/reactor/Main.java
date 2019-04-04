package com.alibaba.demon.reactor;

import java.io.IOException;

/**
 * @author: Demon
 * @create: 2019-04-04
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        new Reactor(8081).run();
    }
}
