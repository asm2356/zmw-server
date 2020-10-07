package com.iflytek.front.web.config;

import com.iflytek.config.service.redis.ChoiceRedisConfig;
import com.iflytek.config.service.redis.RedisService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author AZhao
 */
@Configuration
@EnableCaching
public class RedisConfig extends ChoiceRedisConfig {
}
