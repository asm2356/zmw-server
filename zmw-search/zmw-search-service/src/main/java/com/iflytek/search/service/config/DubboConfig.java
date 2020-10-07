package com.iflytek.search.service.config;

import com.alibaba.dubbo.config.ApplicationConfig;
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
        applicationConfig.setName("zmw-search-service");
        Map<String, String> map = new HashMap<>();
        map.put("qos.enable", "true");
        map.put("qos.accept.foreign.ip", "false");
        map.put("qos.port", SystemConfigUtils.getValue("search.qos.port"));
        applicationConfig.setParameters(map);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol(SystemConfigUtils.getValue("search.dubbo-registry-protocol"));
        registryConfig.setAddress(SystemConfigUtils.getValue("search.dubbo-registry-address"));
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName(SystemConfigUtils.getValue("search.dubbo.protocol.name"));
        protocol.setPort(Integer.parseInt(SystemConfigUtils.getValue("search.dubbo.protocol.port")));
        return protocol;
    }
}
