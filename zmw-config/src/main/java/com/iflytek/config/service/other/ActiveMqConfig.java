package com.iflytek.config.service.other;
import com.iflytek.config.service.app.SystemConfigUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.SimpleMessageConverter;
/**
 * @author AZhao
 */
@Configuration
@EnableJms
public class ActiveMqConfig {
    //重试策略
    @Bean
    public RedeliveryPolicy activeMQRedeliveryPolicy() {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        //重发次数,默认为6次
        redeliveryPolicy.setMaximumRedeliveries(1);
        //重发时间间隔,默认为1秒
        redeliveryPolicy.setMaximumRedeliveryDelay(1000);
        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        redeliveryPolicy.setBackOffMultiplier(2);
        return redeliveryPolicy;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(SystemConfigUtils.getValue("activemq.brokerURL"));
        //异步发送
        activeMQConnectionFactory.setUseAsyncSend(true);
        activeMQConnectionFactory.setRedeliveryPolicy(activeMQRedeliveryPolicy());
        return activeMQConnectionFactory;
    }

    //消息工厂连接池 Connection和Session provider 缓存
    @Bean
    public PooledConnectionFactory jmsFactory() {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(connectionFactory());
        pooledConnectionFactory.setMaxConnections(100);
        return pooledConnectionFactory;
    }

    //缓存消费者
    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(jmsFactory());
        cachingConnectionFactory.setSessionCacheSize(1);
        return cachingConnectionFactory;
    }
    //监听容器工厂
    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(false);
        //factory.setConcurrency("3-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(jmsFactory());
        //spring 默认的转化器
        jmsTemplate.setMessageConverter(new SimpleMessageConverter());
        jmsTemplate.setReceiveTimeout(10000);
        //发布订阅模式
        jmsTemplate.setPubSubDomain(false);
        return jmsTemplate;
    }
}
