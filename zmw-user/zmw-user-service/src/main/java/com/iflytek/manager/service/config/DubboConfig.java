package com.iflytek.manager.service.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
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
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("zmw-manager-service");
        Map<String, String> map = new HashMap<>();
        map.put("qos.enable", "true");
        map.put("qos.accept.foreign.ip", "false");//是否支持远程访问
        map.put("qos.port", SystemConfigUtils.getValue("user.qos.port"));
        applicationConfig.setParameters(map);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol(SystemConfigUtils.getValue("user.dubbo-registry-protocol"));
        registryConfig.setAddress(SystemConfigUtils.getValue("user.dubbo-registry-address"));
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName(SystemConfigUtils.getValue("user.dubbo-protocol.name"));
        protocol.setPort(Integer.parseInt(SystemConfigUtils.getValue("user.dubbo-protocol.port")));
        return protocol;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(10000);
        return consumerConfig;
    }
}
