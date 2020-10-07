package com.iflytek.config.service.redis;

import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.config.service.app.SystemConfigUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
/**
 * @author AZhao
 */
@Configuration
public class RedisClusterConfig extends CachingConfigurerSupport {
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

    public RedisClusterConfiguration redisClusterConfiguration() {
        String nodesStr = SystemConfigUtils.getValue("redisCluster.nodes");
        if (nodesStr == null) {
            return null;
        }
        String[] temp = nodesStr.split(",");
        List<RedisNode> redisNodes = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            String address = temp[i].split(":")[0];
            String port = temp[i].split(":")[1];
            redisNodes.add(new RedisNode(address, Integer.parseInt(port)));
        }
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setClusterNodes(redisNodes);
        String redisClusterPassword = SystemConfigUtils.getValue("redisCluster.password");
        redisClusterConfiguration.setPassword(RedisPassword.of(redisClusterPassword.toCharArray()));
        return redisClusterConfiguration;
    }

    public JedisConnectionFactory redisConnectionFactory() {
        return  new JedisConnectionFactory(redisClusterConfiguration(), jedisConfig());
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10));
        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }

    @Bean
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
