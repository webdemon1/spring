package com.alibaba.demon.databus.data;

import com.alibaba.demon.databus.AbstractDataType;
import com.alibaba.demon.databus.DataType;

import java.time.LocalDateTime;

/**
 * @author: Demon
 * @create: 2019-04-28
 **/
public class StartData extends AbstractDataType {

    private final LocalDateTime time;

    public StartData(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getWhen() {
        return time;
    }

    public static DataType of(final LocalDateTime time) {
        return new StartData(time);
    }
}
