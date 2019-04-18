package com.alibaba.demon.builder;

/**
 * 建造者设计模式
 * @author: Demon
 * @create: 2019-04-18
 **/
public class App {

    public static void main(String[] args) {
        Person person = new Person.Builder()
                        .age(29)
                        .gender("female")
                        .name("Michael").build();

        System.out.println(person);
    }

    /*
     * 试用于对象参数比较多的情况下，返回不可变对象。
     * 不需太多的构造方法,也避免了试用set方法 造成多线程情况下，对象不一致的情况；
     *
     * 优点：
       解耦，逻辑清晰。统一交由 Builder 类构造，Person 类不用关心内部实现细节，只注重结果。
       链式调用，使用灵活，易于扩展。相对于方法一中的构造器方法，配置对象属性灵活度大大提高，
       支持链式调用使得逻辑清晰不少，而且我们需要扩展的时候，也只需要添加对应扩展属性即可，十分方便。
     */

}
