package com.iflytek.manager.service.config;

import com.iflytek.config.service.other.ActiveMqConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * @author AZhao
 */
@Configuration
public class ConsumerActiveMqConfig extends ActiveMqConfig {
}

