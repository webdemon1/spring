package com.alibaba.demon;

import com.alibaba.demon.domain.User;
import com.alibaba.demon.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author: Demon
 * @create: 2019-04-05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisTest {

    @Resource
    private RedisService redisService;

    @Test
    public void testCacheManager() {
        redisService.save2Redis(System.currentTimeMillis());
    }

    @Test
    public void testHash() {
        User user = User.builder().gender("female").id(10L).name("Taylor").build();
        User u1 = redisService.saveOrUpdate(user);
        User u2 = redisService.get(user.getId());
        assertEquals(u1, u2);
        redisService.delete(user.getId());

        User u3 = redisService.get(user.getId());
        assertEquals(null, u3);
    }
}
