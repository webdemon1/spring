package com.alibaba.demon.factory;

/**
 *  汽车生产线工厂
 * @author: Demon
 * @create: 2019-04-20
 **/

public interface CarLineFactory {

    Engine createEngine();

    Seat createSeat();

    Tire createTire();

}
