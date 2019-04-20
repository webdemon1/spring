/**
 * stay foolish, stay young.
 * <p>
 * He who has a "why" to live for can bear almost any "how".
 * <p>
 * love what you do, work hard and make history.
 * <p>
 * Everyday count, because Everyday create your history.
 */
package com.alibaba.demon.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 适配器模式
 * 让一个对象拥有它本身不具备的能力
 *
 * @author: Demon
 * @create: 2019-04-20
 **/
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("------plasticToyDuck-----");
        PlasticToyDuck plasticToyDuck = new PlasticToyDuck();
        plasticToyDuck.squeak();

        log.info("-----sparrow------");

        Sparrow sparrow = new Sparrow();
        sparrow.fly();
        sparrow.makeSound();

        log.info("-----bird behaves like a toy duck ------");

        // Wrap a bird in a birdAdapter, so that it behaves like toy duck
        ToyDuck toyDuck = new BirdAdapter(sparrow);

        // toy duck behaving like a bird
        toyDuck.squeak();
    }

}
