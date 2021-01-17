package org.shield.redis.autoconfigure;

import java.time.Duration;
import org.shield.redis.service.CacheService;
import org.shield.redis.service.impl.CacheServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zacksleo@gmail.com
 */
@EnableCaching
@Configuration
@ConditionalOnProperty(name = "spring.redis.host")
public class RedisAutoConfiguration {
    /**
     * redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类,方便调试redis
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 使用StringRedisSerializer来序列化和反序列化redis的key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisSerializationContext.SerializationPair<Object> jsonSerializer =
                RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
        RedisCacheConfiguration defaultCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1))
                        .computePrefixWith(cacheName -> "caching:" + cacheName).serializeValuesWith(jsonSerializer);

        TtlRedisCacheManager redisCacheManager = new TtlRedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory), defaultCacheConfig);
        return redisCacheManager;
    }

    @Bean
    public CacheService cacheService() {
        return new CacheServiceImpl();
    }
}
