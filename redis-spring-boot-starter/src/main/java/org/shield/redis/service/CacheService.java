package org.shield.redis.service;

import java.util.List;

/**
 * @author zacksleo@gmail.com
 */
public interface CacheService {

    /**
     * 设置缓存
     *
     *
     * @param key
     * @param val
     * @param timeoutSeconds
     * @return
     */
    boolean set(String key, String val, long timeoutSeconds);

    /**
     * 设置缓存
     *
     *
     * @param key
     * @param val
     * @return
     */
    boolean set(String key, String val);

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public boolean delete(String key);

    /**
     * put key value
     * @param <T>
     * @param key
     * @param hk
     * @param t
     */
    <T> void put(String key, String hk, T t);

    /**
     * get value by key
     * @param <T>
     * @param key
     * @param hk
     * @return
     */
    <T> T get(String key, String hk);

    /**
     * put List
     * @param key
     * @param list
     */
    <T> void put(String key, List<T> list);

    /**
     * get list by range
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    <T> List<T> get(String key, long start, long end);

    /**
     * remove by key
     *
     * @param key
     * @return
     */
    boolean remove(String key);
}
