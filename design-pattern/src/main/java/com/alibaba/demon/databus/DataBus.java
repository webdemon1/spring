package com.alibaba.demon.databus;

import com.alibaba.demon.databus.member.Member;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author: Demon
 * @create: 2019-04-28
 **/
public class DataBus {

    private static final DataBus dataBus = new DataBus();

    private final Set<Member> listener = Sets.newHashSet();

    public static DataBus getInstance() {
        return dataBus;
    }

    public void subscribe(final Member member) {
        listener.add(member);
    }

    public void unSubscribe(final Member member) {
        listener.remove(member);
    }

    /**
     * publish event to  all member
     *
     * @param event
     */
    public void publish(DataType event) {
        event.setDataBus(this);
        listener.forEach(listener -> listener.accept(event));
    }
}
