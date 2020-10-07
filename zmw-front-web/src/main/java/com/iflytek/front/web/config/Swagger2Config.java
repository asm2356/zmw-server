package com.iflytek.front.web.config;

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
 * @author AZhao
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.iflytek.front.web.controller"})
public class Swagger2Config {
    /**
     * 创建API 应用
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iflytek.front.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("普通用户的API")
                .description("更多请关注http://172.16.108.116::8081")
                .termsOfServiceUrl("http://172.16.108.116::8081")
                .version("1.0")
                .build();
    }
}
