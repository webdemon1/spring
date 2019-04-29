package com.alibaba.demon.databus.member;

import com.alibaba.demon.databus.DataType;
import com.alibaba.demon.databus.data.MessageData;
import com.alibaba.demon.databus.data.StartData;
import com.alibaba.demon.databus.data.StopData;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author: Demon
 * @create: 2019-04-28
 **/
@Slf4j
public class StatusMember implements Member {

    private final int id;

    private LocalDateTime started;

    private LocalDateTime stopped;

    public StatusMember(int id) {
        this.id = id;
    }

    @Override
    public void accept(DataType data) {
        if (data instanceof StartData) {
            handleEvent((StartData) data);
        } else if (data instanceof StopData) {
            handleEvent((StopData) data);
        }
    }

    private void handleEvent(StartData data) {
        started = data.getWhen();
        log.info("-- {} receiver see application start at {} --", id, started);
    }

    private void handleEvent(StopData data) {
        stopped = data.getWhen();
        log.info("-- {} receiver see application stop at {} --", id, started);
        data.getDataBus().publish(MessageData.of(String.format("--GoodBye cruel world from #%d!--", id)));
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public LocalDateTime getStopped() {
        return stopped;
    }
}
