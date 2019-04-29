package com.alibaba.demon.databus;

import com.alibaba.demon.databus.data.MessageData;
import com.alibaba.demon.databus.data.StartData;
import com.alibaba.demon.databus.data.StopData;
import com.alibaba.demon.databus.member.MessageMember;
import com.alibaba.demon.databus.member.StatusMember;

import java.time.LocalDateTime;

/**
 * * <p>The Data-Bus pattern provides a method where different parts of an application may
 * pass messages between each other without needing to be aware of the other's existence.</p>
 * <p>Similar to the {@code ObserverPattern}, members register themselves with the {@link DataBus}
 * and may then receive each piece of data that is published to the Data-Bus. The member
 * may react to any given message or not.</p>
 * <p>It allows for Many-to-Many distribution of data, as there may be any number of
 * publishers to a Data-Bus, and any number of members receiving the data. All members
 * will receive the same data, the order each receives a given piece of data, is an
 * implementation detail.</p>
 * <p>Members may unsubscribe from the Data-Bus to stop receiving data.</p>
 * <p>This example of the pattern implements a Synchronous Data-Bus, meaning that
 * when data is published to the Data-Bus, the publish method will not return until
 * all members have received the data and returned.</p>
 *
 * @author: Demon
 * @create: 2019-04-28
 **/
public class App {


    public static void main(String[] args) {
        final DataBus bus = DataBus.getInstance();
        bus.subscribe(new StatusMember(1));
        bus.subscribe(new StatusMember(2));
        final MessageMember foo = new MessageMember("Foo");
        final MessageMember bar = new MessageMember("Bar");
        bus.subscribe(foo);
        bus.publish(StartData.of(LocalDateTime.now()));
        bus.publish(MessageData.of("Only Foo should see this"));
        bus.subscribe(bar);
        bus.publish(MessageData.of("Foo and Bar should see this"));
        bus.unSubscribe(foo);
        bus.publish(MessageData.of("Only Bar should see this"));
        bus.publish(StopData.of(LocalDateTime.now()));
    }

}
