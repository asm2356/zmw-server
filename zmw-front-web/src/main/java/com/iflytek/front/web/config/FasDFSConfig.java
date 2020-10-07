package com.iflytek.front.web.config;

import com.iflytek.config.service.other.FastDFSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author AZhao
 */
@Configuration
public class FasDFSConfig {
    @Bean
    public FastDFSClient getFastDFSClient() {
        return new FastDFSClient();
    }
}
