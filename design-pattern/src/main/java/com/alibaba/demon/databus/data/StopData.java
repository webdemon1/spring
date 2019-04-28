package com.alibaba.demon.databus.data;

import com.alibaba.demon.databus.AbstractDataType;
import com.alibaba.demon.databus.DataType;

import java.time.LocalDateTime;

/**
 * @author: Demon
 * @create: 2019-04-28
 **/
public class StopData extends AbstractDataType {
    private LocalDateTime time;

    public StopData(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getWhen() {
        return time;
    }

    public static DataType of(final LocalDateTime time) {
        return new StopData(time);
    }
}
