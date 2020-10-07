package com.iflytek.manager.service.config;

import com.iflytek.config.service.app.SystemConfigUtils;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author AZhao
 */
@Configuration
@ComponentScan("com.iflytek.manager.service.job")
public class XxlJobConfig {
    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobSpringExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        //执行器注册中心地址[选填]，为空则关闭自动注册
        xxlJobSpringExecutor.setAdminAddresses(SystemConfigUtils.getValue("xxl.job.admin.addresses"));
        //执行器AppName[选填]，为空则关闭自动注册
        xxlJobSpringExecutor.setAppName(SystemConfigUtils.getValue("xxl.job.executor.appName"));
        //执行器IP[选填]，为空则自动获取
        xxlJobSpringExecutor.setIp(SystemConfigUtils.getValue("xxl.job.ip"));
        //执行器端口号[选填]，小于等于0则自动获取
        xxlJobSpringExecutor.setPort(Integer.parseInt(SystemConfigUtils.getValue("xxl.job.executor.port")));
        //访问令牌[选填]，非空则进行匹配校验
        xxlJobSpringExecutor.setAccessToken(SystemConfigUtils.getValue("xxl.job.accessToken"));
        //执行器日志路径[选填]，为空则使用默认路径
        xxlJobSpringExecutor.setLogPath(SystemConfigUtils.getValue("xxl.job.executor.logPath"));
        //日志保存天数[选填]，值大于3时生效
        xxlJobSpringExecutor.setLogRetentionDays(Integer.parseInt(SystemConfigUtils.getValue("xxl.job.executor.logRetentionDays")));
        return xxlJobSpringExecutor;
    }
}
