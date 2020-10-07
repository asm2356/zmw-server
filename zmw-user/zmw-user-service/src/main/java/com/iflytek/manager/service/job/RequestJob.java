package com.iflytek.manager.service.job;

import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.config.service.redis.RedisService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author zgzhao
 * 记录每小时的请求量0执行
 * cron  0 0 0 ? * * *
 */
@JobHandler(value = "requestJob")
@Component
public class RequestJob extends IJobHandler {

    @Autowired
    private RedisService redisService;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        Long count = (Long) redisService.get(RedisPrefix.REQUEST_COUNT_HOUR);
        redisService.lSet(RedisPrefix.REQUEST_COUNT_DAY, count);
        redisService.del(RedisPrefix.REQUEST_COUNT_HOUR);
        return new ReturnT<>("success");
    }
}
