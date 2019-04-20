package com.alibaba.demon.factory;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 抽象工厂模式
 *
 * 客户端在不必指定产品的具体类型（发动机、轮胎、座椅）的情况下,
 * 能够创建多个产品族的产品对象。当产品族中新增几个产品,
 * 只需创建产品类以及在对应的工厂类中添加对应的方法即可。
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

    /*
     * The Abstract Factory pattern provides a way to encapsulate a group of individual factories that have a common theme
     * without specifying their concrete classes. In normal usage, the client software creates a concrete implementation of
     * the abstract factory and then uses the generic interface of the factory to create the concrete objects that are part
     * of the theme. The client does not know (or care) which concrete objects it gets from each of these internal
     * factories, since it uses only the generic interfaces of their products. This pattern separates the details of
     * implementation of a set of objects from their general usage and relies on object composition, as object creation is
     * implemented in methods exposed in the factory interface.
     */

}
