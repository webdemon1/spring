package com.alibaba.demon.callback;

/**
 * @author: Demon
 * @create: 2019-04-20
 **/
public abstract class Task {

    public void executeWith(Callback callback) {
        execute();
        if (callback != null) {
            callback.call();
        }
    }

    public abstract void execute();

}
