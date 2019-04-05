package com.alibaba.demon;

import com.alibaba.demon.domain.User;
import com.alibaba.demon.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * redis 基本数据结构
 * <p>
 * key-value
 * hash
 * set
 * list
 * zSet
 *
 * @author: Demon
 * @create: 2019-04-05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@SuppressWarnings("unchecked")
public class RedisTest {

    @Resource
    private RedisService redisService;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testCacheManager() {
        redisService.save2Redis(System.currentTimeMillis());
    }

    @Test
    public void testCache() {
        User user = User.builder().gender("female").id(10L).name("Taylor").build();
        User u1 = redisService.saveOrUpdate(user);
        User u2 = redisService.get(user.getId());
        assertEquals(u1, u2);
        redisService.delete(user.getId());

        User u3 = redisService.get(user.getId());
        assertEquals(null, u3);
    }

    @Test
    public void testHash() {
        User user = User.builder().gender("male").id(1L).name("Jordon").build();
        User u = redisService.hashPutAndGet(user);
        assertEquals(u, user);
        String id = redisService.hGet(user.getId().toString(), "id");
        System.out.println(id);
        redisService.hDel(user.getId().toString(), "id", "gender");
        redisTemplate.delete(id);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testList() {
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPushAll("ued", "a", "b", "c", "d");
        List list = listOperations.range("ued", 0, -1);
        System.out.println(list);
        String value = (String) listOperations.rightPop("ued");
        assertEquals("a", value);
    }

    @Test
    public void testIncrease() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("order", 1, 10, TimeUnit.MINUTES);
        valueOperations.increment("order", 1);
        Object order = valueOperations.get("order");
        assertEquals(2, order);
    }

    @Test
    public void testCommon() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("adv", 1,1,TimeUnit.MINUTES);
        valueOperations.set("al", 2);
        valueOperations.set("ass", 3);
        Set keys = redisTemplate.keys("a*");
        assertEquals(3, keys.size());

        redisTemplate.getExpire("adv");
        redisTemplate.expire("adv", 10, TimeUnit.SECONDS);
    }

}
