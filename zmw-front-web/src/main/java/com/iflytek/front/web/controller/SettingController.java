package com.iflytek.front.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.iflytek.common.annotation.OpLog;
import com.iflytek.common.constant.UserConstant;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.model.Account;
import com.iflytek.common.model.Result;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.manager.api.AccountService;
import com.iflytek.manager.api.UserBaseInfoService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author AZhao
 */
@Controller
public class SettingController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserBaseInfoService userBaseInfoService;

    @RequestMapping(value = "setting")
    public String setting(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return "page/user/login";
        }
        model.addAttribute("userBaseInfo", userBaseInfoService.getUserBaseInfoById(subject.getPrincipal().toString()));
        return "page/setting";
    }

    @ApiOperation(value = "修改用户基本信息", httpMethod = "POST")
    @RequestMapping(value = "/changeUserBaseInfo.do")
    @ResponseBody
    public Result changeUserBaseInfo(String userBaseInfoJson) throws Exception {
        Result result = new Result();
        UserBaseInfo userBaseInfo = JSONObject.parseObject(userBaseInfoJson, UserBaseInfo.class);
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return result.failure(ResultCode.UnLogin);
        }
        userBaseInfo.setMwId(subject.getPrincipal().toString());
        UserBaseInfo userBaseInfoTemp = userBaseInfoService.getUserBaseInfoById(userBaseInfo.getMwId());
        userBaseInfo.setCover(userBaseInfoTemp.getCover());
        userBaseInfo.setHeader(userBaseInfoTemp.getHeader());
        userBaseInfoService.updateUserBaseInfo(userBaseInfo);
        return result.success();
    }

    @OpLog(describe = "密码修改")
    @RequestMapping(value = "/changePwd.do")
    @ApiOperation(value = "密码修改", httpMethod = "POST")
    @ResponseBody
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
