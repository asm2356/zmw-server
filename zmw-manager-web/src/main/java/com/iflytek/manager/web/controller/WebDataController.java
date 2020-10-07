package com.iflytek.manager.web.controller;

import com.iflytek.common.annotation.OpLog;
import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.common.model.Result;
import com.iflytek.config.service.redis.RedisService;
import com.iflytek.config.service.shiro.ShiroSessionService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author AZhao
 */
@RestController
@RequestMapping("/webData")
public class WebDataController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ShiroSessionService shiroSessionService;

    private Logger logger = LoggerFactory.getLogger(WebDataController.class);

    @OpLog(describe = "获取在线人数")
    @RequestMapping("getOnlineUserCount.do")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @Cacheable(value = "getOnlineUserCount" )
    public Result<Long> getOnlineUserCount() {
        Collection<Session> sessionList = shiroSessionService.getActiveSessions();
        logger.info(sessionList.toString());
        logger.info(sessionList.size() + "");
        return new Result<Long>().success((Long) redisService.get(RedisPrefix.ONLINE_COUNT));
    }

    @OpLog(describe = "获取请求次数 每秒")
    @RequestMapping("getRequestCountSecond.do")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)

    public Result<String> getRequestCountSecond() {
        double count = 0;
        long hourSize = redisService.lGetListSize(RedisPrefix.REQUEST_COUNT_DAY);
        for (int i = 0; i < hourSize; i++) {
            Object temp = redisService.lGetIndex(RedisPrefix.REQUEST_COUNT_DAY, i);
            count += Integer.parseInt(temp == null ? "0" : temp.toString());
        }
        double result = count / hourSize / 3600;
        if (result <= 10) {
            DecimalFormat df = new DecimalFormat("0.00");//保留两位小数
            return new Result<String>().success(df.format(result));
        } else {
            int lastResult = (int) result;
            return new Result<String>().success(lastResult + "");
        }

    }

    @OpLog(describe = "获取今日请求总量")
    @RequestMapping("getRequestSumCount.do")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    public Result<Integer> getRequestSumCount() {
        int count = 0;
        long hourSize = redisService.lGetListSize(RedisPrefix.REQUEST_COUNT_DAY);
        for (int i = 0; i < hourSize; i++) {
            Object temp = redisService.lGetIndex(RedisPrefix.REQUEST_COUNT_DAY, i);
            count += Integer.parseInt(temp == null ? "0" : temp.toString());
        }
        Object currentTimeCount = redisService.get(RedisPrefix.REQUEST_COUNT_HOUR);
        count += Integer.parseInt(currentTimeCount == null ? "0" : currentTimeCount.toString());
        return new Result<Integer>().success(count);
    }

    @OpLog(describe = "获取每小时请求的量列表")
    @RequestMapping("getRequestHourList.do")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @Cacheable(value = "getRequestHourList")
    public Result<List<Integer>> getRequestHourList() {
        List<Integer> list = new ArrayList<>();
        int count;
        long hourSize = redisService.lGetListSize(RedisPrefix.REQUEST_COUNT_DAY);
        for (int i = 0; i < hourSize; i++) {
            Object temp = redisService.lGetIndex(RedisPrefix.REQUEST_COUNT_DAY, i);
            count = Integer.parseInt(temp == null ? "0" : temp.toString());
            list.add(count);
        }
        return new Result<List<Integer>>().success(list);
    }
}
