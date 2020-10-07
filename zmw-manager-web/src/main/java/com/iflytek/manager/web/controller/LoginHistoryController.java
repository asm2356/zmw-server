package com.iflytek.manager.web.controller;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.annotation.OpLog;
import com.iflytek.common.model.LoginHistory;
import com.iflytek.common.model.Result;
import com.iflytek.manager.api.LoginHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@RestController
@RequestMapping(value = "loginHistory")
@Api(value = "登录历史")
public class LoginHistoryController {
    @Autowired
    private LoginHistoryService loginHistoryService;

    @OpLog(describe = "获取用户登录历史")
    @ApiOperation(value = "获取用户登录历史", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getLoginHistory.do")
    public Result<List<LoginHistory>> getLoginHistory(@RequestParam(value = "startNum", required = true) int startNum,
                                                      @RequestParam(value = "pageSize", required = true) int pageSize,
                                                      @RequestParam(value = "loginHistoryJson", required = false) String loginHistoryJson,
                                                      @RequestParam(value = "startTime", required = false) Long startTime,
                                                      @RequestParam(value = "endTime", required = false) Long endTime) {
        Result<List<LoginHistory>> result = new Result<>();
        Map<String, Object> map = new HashMap<>();
        LoginHistory loginHistory = JSON.parseObject(loginHistoryJson, LoginHistory.class);
        return result.success(loginHistoryService.getLoginHistory(startNum, pageSize, loginHistory, startTime, endTime));
    }


    @OpLog(describe = "获取用户登录历史数量")
    @ApiOperation(value = "获取用户登录历史数量", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getLoginHistoryNumber.do")

    public Result getLoginHistoryNumber(@RequestParam(value = "loginHistoryJson", required = false) String loginHistoryJson,
                                        @RequestParam(value = "startTime", required = false) Long startTime,
                                        @RequestParam(value = "endTime", required = false) Long endTime) {
        Result result = new Result<>();
        LoginHistory loginHistory = JSON.parseObject(loginHistoryJson, LoginHistory.class);
        return result.success(loginHistoryService.getLoginHistoryNumber(loginHistory, startTime, endTime));
    }

    @OpLog(describe = "删除用户登录历史")
    @ApiOperation(value = "删除用户登录历史", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "deleteLoginHistory.do")
    public Result deleteLoginHistory(String id) {
        Result result = new Result<>();
        return result.success(loginHistoryService.deleteLoginHistory(id));
    }

}
