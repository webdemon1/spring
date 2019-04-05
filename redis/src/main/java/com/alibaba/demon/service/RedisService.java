package com.alibaba.demon.service;

import com.alibaba.demon.domain.User;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 * @author: Demon
 * @create: 2019-04-05
 **/
@Service
@Slf4j
public class RedisService {

    @Resource
    private CacheManager cacheManager;

    private static Map<Long, Object> DB = Maps.newConcurrentMap();


    private final HashOperations<String, Object, Object> hashOperation;
    private final ListOperations<String, Object> listOperation;
    private final ValueOperations<String, Object> valueOperations;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.hashOperation = redisTemplate.opsForHash();
        this.listOperation = redisTemplate.opsForList();
        this.valueOperations = redisTemplate.opsForValue();
    }

    public Long save2Redis(long time) {
        cacheManager.getCache("TEST").put("time", time);
        return (Long) cacheManager.getCache("TEST").get("time").get();
    }

    @CachePut(value = "USER", key = "#user.id")
    public User saveOrUpdate(User user) {
        DB.put(user.getId(), user);
        return user;
    }

    @Cacheable(value = "USER", key = "#id", unless = "#result == null")
    public User get(Long id) {
        log.info("@RedisService.get{}", id);
        return (User) DB.get(id);
    }

    @CacheEvict(value = "USER", key = "#id")
    public void delete(Long id) {
        log.info("@RedisService.delete{}", id);
        DB.remove(id);
    }

    /**  ------ hash ----- */

    public String hGet(String key, String field) {
        return String.valueOf( hashOperation.get(key, field).toString());
    }

    public void hSet(String key, String field, Object value) {
        hashOperation.put(key, field, value);
    }

    public void hDel(String key, Object... fields) {
        hashOperation.delete(key, fields);
    }

    public Map<Object, Object> hGetAll(String key) {
        return hashOperation.entries(key);
    }
    public User hashPutAndGet(User user) {
        Map<String, Object> userMap = JSON.parseObject(JSON.toJSONString(user));
        hashOperation.putAll(user.getId().toString(), userMap);
        Object id = hashOperation.get(user.getId().toString(), "id");
        Object name = hashOperation.get(user.getId().toString(), "name");
        Object gender = hashOperation.get(user.getId().toString(), "gender");

        Map<Object, Object> entries = hashOperation.entries(user.getId().toString());
        System.out.println(entries);
        return User.builder().name(name.toString()).id(Long.valueOf(id.toString()))
                .gender(gender.toString()).build();
    }

}
