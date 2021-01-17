package org.shield.redis.autoconfigure;

import java.time.Duration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.StringUtils;

/**
 * 将缓存中形如 name@\d+ 格式的注解，自动设置过期时间, 如
 *
 * <pre>
 * @Cacheable(value = "roomStateCache@600", key = "':' + #root.targetClass.getName() + ':' + #roomId")
 * </pre>
 *
 * 将会设置为600秒自动过期
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public class TtlRedisCacheManager extends RedisCacheManager {

    public TtlRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        String[] array = StringUtils.delimitedListToStringArray(name, "@");
        name = array[0];
        if (array.length > 1) {
            long ttl = Long.parseLong(array[1]);
            cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
        }
        return super.createRedisCache(name, cacheConfig);
    }
}
