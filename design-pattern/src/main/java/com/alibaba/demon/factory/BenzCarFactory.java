package com.alibaba.demon.factory;

/**
 * @author: Demon
 * @create: 2019-04-20
 **/
public class BenzCarFactory implements CarLineFactory {

    @Override
    public Engine createEngine() {
        return new BmwEngine();
    }

    @Override
    public Seat createSeat() {
        return new BenzSeat();
    }

    @Override
    public Tire createTire() {
        return new BenzTire();
    }
}
