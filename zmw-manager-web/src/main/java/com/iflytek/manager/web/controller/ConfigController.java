package com.iflytek.manager.web.controller;

import com.iflytek.common.annotation.OpLog;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.exception.DBException;
import com.iflytek.common.model.Config;
import com.iflytek.common.model.Result;
import com.iflytek.manager.api.ConfigService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AZhao
 */
@RestController
@RequestMapping(value = "config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @OpLog(describe = "添加配置")
    @RequestMapping(value = "addConfig.do")
    @RequiresRoles(value = {"superAdmin"})
    public Result<Integer> addConfig(String key, String value, String describe) {
        Result<Integer> result = new Result<>();
        try {
            return result.success(configService.addConfig(new Config(key, value, describe)));
        } catch (DBException e) {
            return result.failure(ResultCode.Key_Has_Exist);
        }
    }

    @OpLog(describe = "修改配置")
    @RequestMapping(value = "updateConfig.do")
    @RequiresRoles(value = {"superAdmin"})
    public Result updateConfig(Config config) {
        return new Result<>().success(configService.updateConfig(config));
    }

    @OpLog(describe = "删除配置")
    @RequestMapping(value = "deleteConfig.do")
    @RequiresRoles(value = {"superAdmin"})
    public Result deleteConfig(String key) {
        return new Result<Integer>().success(configService.deleteConfig(key));
    }

    @OpLog(describe = "获取配置列表")
    @RequestMapping(value = "getConfigList.do")
    @RequiresRoles(value = {"superAdmin"})
    public Result<List<Config>> getConfigList(int startNum, int pageSize, String key, String value, String describe) {
        return new Result<List<Config>>().success(configService.getConfigList(startNum, pageSize, key, value, describe));
    }

    @RequestMapping(value = "getConfigNumber.do")
    @RequiresRoles(value = {"superAdmin"})
    public Result<Integer> getConfigNumber(String key, String value, String describe) {
        return new Result<Integer>().success(configService.getConfigNumber(key, value, describe));
    }

}
