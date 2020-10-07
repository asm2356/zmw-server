package com.iflytek.manager.service.job;

import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.common.enumeration.sys.StatisticsType;
import com.iflytek.common.model.Statistics;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.config.service.redis.RedisService;
import com.iflytek.manager.api.AccountService;
import com.iflytek.manager.api.StatisticsService;
import com.iflytek.manager.api.UserBaseInfoService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zgzhao
 * cron 0 0 0 * * ?
 */
@JobHandler(value = "statisticsJob")
@Component
public class StatisticsJob extends IJobHandler {
    private Logger logger = LoggerFactory.getLogger(StatisticsJob.class);

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private AccountService accountService;

    @Override
    public ReturnT<String> execute(String arg) throws Exception {
        //新的一天
        redisService.set(RedisPrefix.REQUEST_COUNT, 0);
        redisService.set(RedisPrefix.REQUEST_COUNT_HOUR, 0);
        redisService.clear(RedisPrefix.REQUEST_COUNT_DAY);


        Statistics statistics = new Statistics();
        statistics.setDate(new Date());

        //每周的第几天
        long currentWeekDay = redisService.incr(RedisPrefix.CURRENT_WEEK_DAY, 1);
        if (currentWeekDay % 8 == 0) {
            redisService.incr(RedisPrefix.CURRENT_WEEK, 1);
            redisService.set(RedisPrefix.CURRENT_WEEK_DAY, 1);
        }
        logger.info(redisService.get(RedisPrefix.CURRENT_WEEK_DAY) + "==>day");
        logger.info(redisService.get(RedisPrefix.CURRENT_WEEK) + "==>week");
        int currentWeek = Integer.parseInt(String.valueOf(redisService.get(RedisPrefix.CURRENT_WEEK)));

        statistics.setWeek(currentWeek);
        int result = 0;

        //用户活跃率
        statistics.setId(RandomUtils.getUUID());
        statistics.setType(StatisticsType.Active_Rate.getValue());
        if (redisService.hasKey(RedisPrefix.REQUEST_COUNT)) {
            statistics.setCount(Integer.parseInt(String.valueOf(redisService.get(RedisPrefix.REQUEST_COUNT))));
            redisService.set(RedisPrefix.REQUEST_COUNT, 0);
            result = statisticsService.addStatistics(statistics);
        }

        //新增用户
        statistics.setId(RandomUtils.getUUID());
        statistics.setType(StatisticsType.Add_User.getValue());
        if (redisService.hasKey(RedisPrefix.ADD_USER)) {
            statistics.setCount(Integer.parseInt(String.valueOf(redisService.get(RedisPrefix.ADD_USER))));
            result += statisticsService.addStatistics(statistics);
        }

        //用户总量
        statistics.setId(RandomUtils.getUUID());
        statistics.setType(StatisticsType.User_Sum.getValue());
        int newAddCount = Integer.parseInt(String.valueOf(redisService.get(RedisPrefix.ADD_USER)));
        int userSumCount = 0;
        try {
            userSumCount = Integer.parseInt(String.valueOf(redisService.get(RedisPrefix.USER_SUM)));
            statistics.setCount(newAddCount + userSumCount);
        } catch (Exception e) {
            userSumCount = accountService.getUserCount();
            statistics.setCount(userSumCount);
        }
        redisService.set(RedisPrefix.ADD_USER, 0);
        redisService.set(RedisPrefix.USER_SUM, statistics.getCount());
        result += statisticsService.addStatistics(statistics);
        if (result > 2) {
            return new ReturnT<>(true + "");
        } else {
            return new ReturnT<>(false + "");
        }
    }
}
