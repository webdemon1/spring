/**
 *
 stay foolish, stay young.

 He who has a "why" to live for can bear almost any "how".

 love what you do, work hard and make history.

 Everyday count, because Everyday create your history.

 */
package com.alibaba.demon.singleton;

/**
 *
 * 单例
 * @author: Demon
 * @create: 2019-04-20
 **/
public class Singleton {

    private Singleton(){}

    private static final Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

}
