package com.iflytek.config.service.redis;

import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.config.service.app.SystemConfigUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author AZhao
 */
@EnableCaching
public class RedisStandaloneConfig extends CachingConfigurerSupport {
    public JedisPoolConfig jedisConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //#最大空闲数，数据库连接的最大空闲时间。超过空闲时间，数据库连接将被标记为不可用，然后被释放。设为0表示无限制。
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxTotal(80);//最大连接数
        //maxActive  最大可连接的客户端数量
        //maxWait 连接池中连接用完时,新的请求等待时间,毫秒
        //maxIdle 连接池中最多可空闲maxIdle个连接 即使没有数据库连接时依然可以保持20空闲的连接，而不被清除，随时处于待命状态。设 0 为没有限制。
        //#最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
        jedisPoolConfig.setMaxWaitMillis(10000);
        //#在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的/检查redis连接有效性/
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration RedisConfiguration =
                new RedisStandaloneConfiguration();
        RedisConfiguration.setHostName(SystemConfigUtils.getValue("redisStandalone.host"));
        RedisConfiguration.setPassword(RedisPassword.of(SystemConfigUtils.getValue("redisStandalone.password")));
        RedisConfiguration.setPort(Integer.parseInt(SystemConfigUtils.getValue("redisStandalone.port")));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpcf.poolConfig(jedisConfig());
        JedisClientConfiguration jedisClientConfiguration = jpcf.build();
        return new JedisConnectionFactory(RedisConfiguration, jedisClientConfiguration);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        //redis 缓存时间
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10));
        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(RedisPrefix.SPRING_REDIS);
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}
