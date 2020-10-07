package com.iflytek.manager.service.init;

import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.common.exception.DeleteFileException;
import com.iflytek.common.model.DFSFile;
import com.iflytek.config.service.other.FastDFSClient;
import com.iflytek.config.service.redis.RedisService;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;

/**
 * @author AZhao
 */
@Component
public class ApplicationInit {
    @Autowired
    private RedisService redisService;
    @PostConstruct
    public void iniRedis() {
        redisService.set(RedisPrefix.CURRENT_WEEK_DAY, 0);//当前周的第几天
        redisService.set(RedisPrefix.CURRENT_WEEK, 0);//当前周
        redisService.set(RedisPrefix.USER_SUM, 0);//用户总量
        redisService.set(RedisPrefix.ONLINE_COUNT,0);//在线用户量
    }
}
