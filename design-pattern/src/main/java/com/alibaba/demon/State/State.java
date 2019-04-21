package com.alibaba.demon.State;

/**
 * @author: Demon
 * @create: 2019-04-21
 **/
public interface State {

    void onEnterState();

    void observe();
}
