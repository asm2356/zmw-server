package com.iflytek.manager.web.controller;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.model.Permission;
import com.iflytek.common.model.Result;
import com.iflytek.common.model.Role;
import com.iflytek.common.utils.MyUtils;
import com.iflytek.manager.api.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AZhao
 */
@RestController
@RequestMapping(value = "authorization")
@Api(value = "权限角色管理")
public class AuthorizationController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "getRoleList.do")
    @ApiOperation(value = "查询角色列表", httpMethod = "POST")
    @ApiImplicitParam(name = "mwId", value = "mwId,为空查询系统所有角色,不为空查询某个用户的角色列表")
    public Result<List<Role>> getRoleList(@RequestParam(value = "mwId", required = false) String mwId) {
        Result<List<Role>> result = new Result<>();
        return result.success(accountService.getRoleList(mwId));
    }

   @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @ApiOperation(value = "查看权限系统所有的权限列表", httpMethod = "POST")
    @RequestMapping(value = "getPermissionList.do")
    public Result<List<Permission>> getPermissionList() {
        Result<List<Permission>> result = new Result<>();
        return result.success(accountService.getPermissionList());
    }

    @ApiOperation(value = "移除某个用户的角色", httpMethod = "POST")
    @RequiresRoles(value = { "superAdmin"})
    @RequestMapping(value = "removeRole.do")
    public Result<Integer> removeRole(String mwId, String roleIdListJson) {
        Result<Integer> result = new Result<>();
        return result.success(accountService.removeRole(mwId, MyUtils.parseRequestList(roleIdListJson)));
    }

    @ApiOperation(value = "移除某个用户所有角色", httpMethod = "POST")
    @RequiresRoles(value = { "superAdmin"})
    @RequestMapping(value = "removeAllRole.do")
    public Result<Integer> removeAllRole(String mwId) {
        Result<Integer> result = new Result<>();
        return result.success(accountService.removeAllRole(mwId));
    }

    @ApiOperation(value = "为某个用户添加某些权限", httpMethod = "POST")
    @RequiresPermissions(value = "addPermission")
    @RequestMapping(value = "addPermission.do")
    public Result<Integer> addPermission(String mwId, String permissionIdListJson) {
        Result<Integer> result = new Result<>();
        return result.success(accountService.addPermission(mwId, MyUtils.parseRequestList(permissionIdListJson)));
    }

    @ApiOperation(value = "为某个用户添加某些角色", httpMethod = "POST")
    @RequiresRoles(value = { "superAdmin"})
    @RequestMapping(value = "addRole.do")
    public Result<Integer> addRole(String mwId, String roleIdListJson) {
        Result<Integer> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }

        return result.success(accountService.addRole(mwId, MyUtils.parseRequestList(roleIdListJson)));
    }

    @ApiOperation(value = "移除某个用户所有权限", httpMethod = "POST")
    @RequiresPermissions(value = "removeAllPermission")
    @RequestMapping(value = "removeAllPermission.do")
    public Result<Integer> removeAllPermission(String mwId) {
        return new Result<Integer>().success(accountService.removeAllPermission(mwId));
    }

    @ApiOperation(value = "移除用户某些权限", httpMethod = "POST")
    @RequiresPermissions(value = "removePermission")
    @RequestMapping(value = "removePermission.do")
    public Result<Integer> removePermission(String mwId, String permissionIdListJson) {
        return new Result<Integer>().success(accountService.removePermission(mwId, MyUtils.parseRequestList(permissionIdListJson)));
    }
}
