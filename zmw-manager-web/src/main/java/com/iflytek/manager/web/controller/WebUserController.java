package com.iflytek.manager.web.controller;

import com.iflytek.common.annotation.OpLog;
import com.iflytek.common.enumeration.sys.StatisticsType;
import com.iflytek.common.model.Config;
import com.iflytek.common.model.Result;
import com.iflytek.common.model.Statistics;
import com.iflytek.common.model.vo.ActiveUser;
import com.iflytek.manager.api.ConfigService;
import com.iflytek.manager.api.StatisticsService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AZhao
 */
// TODO: 用户活跃率
@RestController
@RequestMapping(value = "webUser")
public class WebUserController {
    @Autowired
    private StatisticsService statisticsService;

    @OpLog(describe = "获取用户活跃率")
    @ApiOperation(value = "获取用户活跃率", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getActivityRate.do")
    @Cacheable(value = "getActivityRate" ,key = "#timeType")
    public Result<List<Statistics>> getActivityRate(int timeType) {
        Result<List<Statistics>> result = new Result<>();
        return result.success(statisticsService.getStatistics(StatisticsType.Active_Rate.getValue(), timeType));
    }

    @OpLog(describe = "获取新增的用户趋势")
    @ApiOperation(value = "获取新增的用户趋势", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getAddUser.do")
    public Result<List<Statistics>> getAddUser(int timeType) {
        Result<List<Statistics>> result = new Result<>();
        return result.success(statisticsService.getStatistics(StatisticsType.Add_User.getValue(), timeType));
    }

    @OpLog(describe = "获取总用户的趋势")
    @ApiOperation(value = "获取总用户的趋势", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getUserSum.do")
    @Cacheable(value = "getUserSum" ,key = "#timeType")
    public Result<List<Statistics>> getUserSum(int timeType) {
        Result<List<Statistics>> result = new Result<>();
        return result.success(statisticsService.getStatistics(StatisticsType.User_Sum.getValue(), timeType));
    }

    @OpLog(describe = "获取某些用户活跃率排行")
    @ApiOperation(value = "获取某些用户活跃率排行", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getActivityRanking.do")
    @Cacheable(value = "getActivityRanking" ,key = "#number")
    public Result<List<ActiveUser>> getActivityRanking(int number) {
        Result<List<ActiveUser>> result = new Result<>();
        return result.success(statisticsService.getActivityRanking(number));
    }
}
