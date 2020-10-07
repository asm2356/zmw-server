package com.iflytek.manager.web.controller;

import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.model.Result;
import com.iflytek.search.api.OpLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AZhao
 */
@RestController
@RequestMapping(value = "opLog")
@Api(value = "操作日志")
public class OpLogController {
    @Autowired
    private OpLogService opLogService;

    //@OpLog(describe = "获取用户操作日志")
    @ApiOperation(value = "获取用户操作日志", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getOpLog.do")
    public Result<List> getOpList(int startNum, int pageSize, String condition) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result<List>().failure(ResultCode.UnLogin);
        }
        return new Result<List>().success(opLogService.getLog(startNum, pageSize, subject.getPrincipal().toString(), condition));
    }

    //@OpLog(describe = "删除用户操作日志")
    @ApiOperation(value = "删除用户操作日志", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequiresPermissions(value = "deleteOpLog")
    @RequestMapping(value = "deleteOpLog.do")
    public Result<Integer> deleteOpLog(String id) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result<Integer>().failure(ResultCode.UnLogin);
        }
        return new Result<Integer>().success(opLogService.deleteLog(id));
    }

    //@OpLog(describe = "获取用户操作日志个数")
    @ApiOperation(value = "获取用户操作日志个数", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getOpLogNumber.do")
    public Result<Integer> getOpLogNumber(String condition) {
        Result<Integer> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        return result.success(opLogService.getOpLogNumber(subject.getPrincipal().toString(), condition));
    }
}
