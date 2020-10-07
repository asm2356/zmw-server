package com.iflytek.manager.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zgzhao
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.iflytek.manager.web.controller"})
public class Swagger2Config {
    /**
     * 创建API 应用
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iflytek.manager.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("管理员的API")
                .description("更多请关注http://http://192.168.52.132::8282")
                .termsOfServiceUrl("http://192.168.52.132::8282")
                .version("1.0")
                .build();
    }
}
