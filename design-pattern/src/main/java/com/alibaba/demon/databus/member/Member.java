package com.alibaba.demon.databus.member;

import com.alibaba.demon.databus.DataType;

import java.util.function.Consumer;

/**
 * @author: Demon
 * @create: 2019-04-28
 **/
public interface Member extends Consumer<DataType> {

    void accept(DataType dataType);

}
