package com.alibaba.demon.service;

import com.alibaba.demon.domain.User;
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
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: Demon
 * @create: 2019-04-05
 **/
@Service
@Slf4j
public class RedisService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CacheManager cacheManager;

    private static Map<Long,Object> DB = Maps.newConcurrentMap();


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

    @CachePut(value = "USER",key = "#user.id")
    public User saveOrUpdate(User user) {
        DB.put(user.getId(),user);
        return user;
    }

    @Cacheable(value = "USER", key = "#id",unless = "#result == null")
    public User get(Long id) {
        log.info("@RedisService.get{}",id);
        return (User) DB.get(id);
    }

    @CacheEvict(value = "USER", key = "#id")
    public void delete(Long id) {
        log.info("@RedisService.delete{}",id);
        DB.remove(id);
    }

    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public long incr(String key, long delta) {
        return valueOperations.increment(key, delta);
    }


    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public void set(String key, String value) {
        valueOperations.set(key, value);
    }

    public void set(String key, String value, long timeout) {
        valueOperations.set(key, value, timeout, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        return valueOperations.get(key);
    }

    public void hSet(String key, String field, Object value) {
        hashOperation.put(key, field, value);
    }

    public String hGet(String key, String field) {
        return (String) hashOperation.get(key, field);
    }

    public void hDel(String key, Object... fields) {
        hashOperation.delete(key, fields);
    }


    public Map<Object, Object> hGetAll(String key) {
        return hashOperation.entries(key);
    }

    public long lPush(String key, String value) {
        return listOperation.leftPush(key, value);
    }

    public String lPop(String key) {
        return (String) listOperation.leftPop(key);
    }

    public long rPush(String key, String value) {
        return listOperation.rightPush(key, value);
    }

}
