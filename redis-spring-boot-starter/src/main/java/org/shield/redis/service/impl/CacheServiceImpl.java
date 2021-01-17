package org.shield.redis.service.impl;

import org.shield.redis.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zacksleo@gmail.com
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.cache.redis.time-to-live:60000}")
    private long timeToLive;


    public boolean set(String key, String val, long timeoutSeconds) {
        stringRedisTemplate.opsForValue().set(key, val, timeoutSeconds, TimeUnit.SECONDS);
        return true;
    }

    public boolean set(String key, String val) {
        stringRedisTemplate.opsForValue().set(key, val, timeToLive, TimeUnit.SECONDS);
        return true;
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public <T> void put(String key, String hk, T t) {
        redisTemplate.opsForHash().put(key, hk, t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, String hk) {
        return (T)redisTemplate.opsForHash().get(key, hk);
    }

    @Override
    public void put(String key, List list) {
        redisTemplate.opsForList().rightPush(key, list);
    }

    @Override
    public List get(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public boolean remove(String key) {
        return redisTemplate.delete(key);
    }
}
