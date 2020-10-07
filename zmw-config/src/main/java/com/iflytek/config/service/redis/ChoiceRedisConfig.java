package com.iflytek.config.service.redis;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author AZhao
 */
public class ChoiceRedisConfig extends RedisStandaloneConfig {
    @Bean
    public JedisPoolConfig jedisConfig(){
        return super.jedisConfig();
    }
    @Bean
    public JedisConnectionFactory redisConnectionFactory(){
        return super.redisConnectionFactory();
    }
    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(this.redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
    @Bean
    public RedisService redisService() {
        return new RedisService();
    }
}
