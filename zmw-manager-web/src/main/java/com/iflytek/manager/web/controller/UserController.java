package com.iflytek.manager.web.controller;

import com.iflytek.common.annotation.OpLog;
import com.iflytek.common.constant.UserConstant;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.model.Account;
import com.iflytek.common.model.Result;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.common.model.vo.ManagerUser;
import com.iflytek.manager.api.AccountService;
import com.iflytek.manager.api.UserBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AZhao
 */
@RestController
@RequestMapping(value = "user")
@Api(value = "用户管理")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserBaseInfoService userBaseInfoService;

    @OpLog(describe = "获取用户列表")
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getUserList.do")
    public Result<List<ManagerUser>> getUserList(@RequestParam("startNum") int startNum,
                                                 @RequestParam("pageSize") int pageSize,
                                                 @RequestParam(value = "roleName", required = false) String roleName,
                                                 @RequestParam(value = "permissionName", required = false) String permissionName,
                                                 @RequestParam(value = "mwId", required = false) String mwId,
                                                 @RequestParam(value = "alias", required = false) String alias) {
        Result<List<ManagerUser>> result = new Result<>();
        return result.success(accountService.getManagerUserList(startNum, pageSize, roleName, permissionName, mwId, alias));
    }

    @ApiOperation(value = "获取关于用户列表数量", httpMethod = "POST")
    @RequiresRoles(value = {"admin", "superAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "getManagerUserNumber.do")
    public Result<Integer> getManagerUserNumber(String roleName, String permissionName, String mwId, String alias) {
        Result<Integer> result = new Result<>();
        return result.success(accountService.getManagerUserNumber(roleName, permissionName, mwId, alias));
    }

    @OpLog(describe = "修改用户当前状态")
    @ApiOperation(value = "修改用户当前状态，禁用，恢复", httpMethod = "POST")
    @RequiresPermissions({"changeUserStatus"})
    @RequestMapping(value = "changeUserStatus.do")
    public Result<Integer> changeUserStatus(String mwId, int status) {
        Result<Integer> result = new Result<>();
        Account account = accountService.findAccountByMwId(mwId);
        account.setStatus(status);
        return result.success(accountService.updateAccount(account));
    }

    @ApiOperation(value = "获取用户基本信息", httpMethod = "POST")
    @RequestMapping(value = "/getUserBaseInfo.do")
    public Result<UserBaseInfo> getUserBaseInfo(String mwId) {
        Result<UserBaseInfo> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (mwId == null || mwId.equals("")) {
            if (subject.getPrincipal() == null) {
                return result.failure(ResultCode.UnLogin);
            } else {
                mwId = subject.getPrincipal().toString();
            }
        }
        UserBaseInfo userBaseInfo = userBaseInfoService.getUserBaseInfoById(mwId);
        if (userBaseInfo != null) {
            return result.success(userBaseInfo);
        } else {
            return result.failure(ResultCode.Unknown_User);
        }
    }

    @OpLog(describe = "密码修改")
    @RequestMapping(value = "/changePwd.do")
    @ApiOperation(value = "密码修改", httpMethod = "POST")
    public Result<Integer> changePwd(String originalPwd, String newPwd) {
        Result<Integer> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        Account account = accountService.findAccountByMwId(subject.getPrincipal().toString());
        String pwd = new Md5Hash(originalPwd, UserConstant.USER_PWD_SALT).toString();
        if (!pwd.equals(account.getPwd())) {
            return result.failure(ResultCode.Pwd_Error);
        }
        newPwd = new Md5Hash(newPwd, UserConstant.USER_PWD_SALT).toString();
        account.setPwd(newPwd);
        return result.success(accountService.updateAccount(account));
    }

}
