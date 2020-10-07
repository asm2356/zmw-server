package com.iflytek.manager.service.config;

import com.iflytek.config.service.redis.ChoiceRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author AZhao
 */
@Configuration
@EnableCaching
public class ManagerServiceRedisConfig extends ChoiceRedisConfig {
}
