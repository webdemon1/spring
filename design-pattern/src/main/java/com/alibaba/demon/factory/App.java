package com.alibaba.demon.factory;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 抽象工厂模式
 * @author: Demon
 * @create: 2019-04-20
 **/
@Slf4j
public class App {

    public static void main(String[] args) {
        CarLineFactory benzCarFactory = new BenzCarFactory();
        Engine benzEngine = benzCarFactory.createEngine();
        benzEngine.run();
        Seat benzSeat = benzCarFactory.createSeat();
        benzSeat.seatDown();
        Tire benzTire = benzCarFactory.createTire();
        benzTire.use();

        log.info("-------------------------");

        CarLineFactory bmwCarFactory = new BmwCarFactory();
        Engine bmwEngine = bmwCarFactory.createEngine();
        bmwEngine.run();
        Seat bmwSeat = bmwCarFactory.createSeat();
        bmwSeat.seatDown();
        Tire bmwTire = bmwCarFactory.createTire();
        bmwTire.use();
    }

}
