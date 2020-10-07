package com.iflytek.manager.service.test;

import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.config.service.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Random;

/**
 * @author AZhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/managerServiceContext.xml"})
public class RedisTest {
    private Logger logger = LoggerFactory.getLogger(RedisTest.class);
    @Autowired
    private RedisService redisService;

    @Test
    public void redisGet() {
        for (int i = 0; i < 100; i++) {
            redisService.set("name" + i, i);
            redisService.incr("name" + i, 1);
            logger.info(redisService.get("name" + i) + "");
        }
    }

    @Test
    public void del() {
        redisService.del("article_reading_number640b70ec669e473590c07ea2372ba536");
    }

    @Test
    public void listTest() {
        redisService.lSet("course", "语文");
        redisService.lSet("course", "数学");
        redisService.lSet("course", "英语");
        logger.info(redisService.lGetListSize("course") + "");
        redisService.clear("course");
        logger.info(redisService.lGetListSize("course") + "");
    }

    @Test
    public void createModeData() {
        redisService.set(RedisPrefix.ONLINE_COUNT, 11012);
        redisService.clear(RedisPrefix.REQUEST_COUNT_DAY);
        logger.info(redisService.lGetListSize(RedisPrefix.REQUEST_COUNT_DAY) + "----------");
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int p = random.nextInt(5);
            int count = random.nextInt(100 * p + 1) + random.nextInt(200);
            redisService.set(RedisPrefix.REQUEST_COUNT_HOUR, count * 10);
            redisService.lSet(RedisPrefix.REQUEST_COUNT_DAY, redisService.get(RedisPrefix.REQUEST_COUNT_HOUR));
        }
        logger.info(redisService.lGetIndex(RedisPrefix.REQUEST_COUNT_DAY, 0) + "");
        logger.info(redisService.lGetListSize(RedisPrefix.REQUEST_COUNT_DAY) + "----------");
    }
}
