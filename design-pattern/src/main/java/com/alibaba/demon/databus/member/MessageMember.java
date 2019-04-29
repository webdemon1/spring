package com.alibaba.demon.databus.member;

import com.alibaba.demon.databus.DataType;
import com.alibaba.demon.databus.data.MessageData;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author: Demon
 * @create: 2019-04-28
 **/
@Slf4j
public class MessageMember implements Member {

    private final String name;

    private List<String> messages = Lists.newArrayList();

    public MessageMember(String name) {
        this.name = name;
    }

    @Override
    public void accept(DataType dataType) {
        if (dataType instanceof MessageData) {
            handleEvent((MessageData) dataType);
        }
    }

    private void handleEvent(MessageData messageData) {
        log.info("-- {} see message {} --", name, messageData.getMessage());
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
