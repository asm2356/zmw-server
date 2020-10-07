package com.iflytek.manager.web.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.iflytek.config.service.app.SystemConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AZhao
 */
@Configuration
public class DubboConfig {
    private SystemConfigUtils systemConfigUtils = null;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("zmw-manager-web");
        Map<String, String> map = new HashMap<>();
        map.put("qos.enable", "true");
        map.put("qos.accept.foreign.ip", "false");
        map.put("qos.port", SystemConfigUtils.getValue("manager-web.qos.port"));
        applicationConfig.setParameters(map);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol(SystemConfigUtils.getValue("manager-web.dubbo-registry-protocol"));
        registryConfig.setAddress(SystemConfigUtils.getValue("manager-web.dubbo-registry-address"));
        return registryConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(10000);
        return consumerConfig;
    }
}
