package com.alibaba.demon.databus.data;

import com.alibaba.demon.databus.AbstractDataType;
import com.alibaba.demon.databus.DataType;

/**
 * @author: Demon
 * @create: 2019-04-28
 **/
public class MessageData extends AbstractDataType {

    private String message;

    public MessageData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static DataType of(String message) {
        return new MessageData(message);
    }
}
