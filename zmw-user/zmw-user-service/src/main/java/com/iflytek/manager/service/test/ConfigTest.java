package com.iflytek.manager.service.test;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.exception.DBException;
import com.iflytek.common.model.Config;
import com.iflytek.manager.api.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author AZhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/managerServiceContext.xml"})

public class ConfigTest {
    private Logger logger = LoggerFactory.getLogger(ConfigTest.class);
    @Autowired
    private ConfigService configService;

    @Test
    public void addConfig() {
        Config config = new Config();
        config.setKey("111111111111111111111111");
        config.setValue("222222222222222222222");
        config.setDescribe("3333333333333333333333");
        try {
            logger.info(configService.addConfig(config) + "");
        } catch (DBException e) {
        }
    }

    @Test
    public void updateConfig() {
        Config config = new Config();
        config.setKey("111111111111111111111111");
        config.setValue("数据2库");
        config.setDescribe("数据库变更类型");
        logger.info(configService.updateConfig(config) + "");
    }

    @Test
    public void deleteConfig() {
        logger.info(configService.deleteConfig("111111111111111111111111") + "");
    }

    @Test
    public void getConfigList() {
        logger.info(JSON.toJSONString(configService.getConfigList(0, 5, "search.qos.port", null, null)));
    }

    @Test
    public void getConfigNumber() {
        logger.info(""+configService.getConfigNumber("redis", "123", "端口"));
    }

}
