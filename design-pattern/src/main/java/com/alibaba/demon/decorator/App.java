package com.alibaba.demon.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Demon
 * @create: 2019-05-04
 **/
@Slf4j
public class App {

    public static void main(String[] args) {

        log.info("A simple looking troll approaches.");
        Troll troll = new SimpleTroll();
        troll.attack();
        troll.fleeBattle();
        log.info("Simple troll power {}.\n", troll.getAttackPower());

        log.info("A troll with huge club surprises you.");
        Troll clubbedTroll = new ClubbedTroll(troll);
        clubbedTroll.attack();
        clubbedTroll.fleeBattle();
        log.info("Clubbed troll power {}.\n", clubbedTroll.getAttackPower());
    }

}
