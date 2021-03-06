package com.iflytek.manager.web.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.iflytek.common.annotation.OpLog;
import com.iflytek.common.annotation.RequestLimit;
import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.common.constant.UserConstant;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.exception.UserNameNotFoundException;
import com.iflytek.common.model.Account;
import com.iflytek.common.model.LoginHistory;
import com.iflytek.common.model.Result;
import com.iflytek.common.utils.ClientUtil;
import com.iflytek.common.utils.IPUtil;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.config.service.redis.RedisService;
import com.iflytek.manager.api.AccountService;
import com.iflytek.manager.api.LoginHistoryService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
/**
 * @author AZhao
 */
@RestController
@Configuration
@EnableAsync
public class LoginController {
    @Autowired
    private RedisService redisService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginHistoryService loginHistoryService;

    @OpLog(describe = "用户登录")
    @RequestLimit(count = 20, time = 86400)
    @RequestMapping(value = "/login.do")
    @ApiOperation(value = "用户登录", notes = "接口发布说明", httpMethod = "POST")
    public Result<String> login(String username, String pwd, String key, String code, HttpServletRequest request) {
        Result<String> result = new Result<>();
        if (!redisService.hasKey(key)) {
            return new Result<>(ResultCode.Verification_Code_Expire);
        }
        String value = (String) redisService.get(key);
        logger.info(value);
        if (!value.toLowerCase().equals(code.toLowerCase())) {
            logger.info("验证码错误");
            return new Result<>(ResultCode.Verification_Code_Error);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, new Md5Hash(pwd, UserConstant.USER_PWD_SALT).toString());
        try {
            logger.info("登录开始");
            long startTime = System.currentTimeMillis();
            // TODO: 权限角色设置
            subject.login(usernamePasswordToken);
            clearLoginLimit(request);
            saveLoginHistory(username, request);
            long time = System.currentTimeMillis() - startTime;
            logger.info("用时" + time);
            return result.success(subject.getPrincipal().toString());
        } catch (LockedAccountException e) {
            logger.info("锁定账号");
            return result.failure(ResultCode.Locked_Account);
        } catch (IncorrectCredentialsException | UserNameNotFoundException e) {
            logger.info("错误的凭证");
            return result.failure(ResultCode.Incorrect_Credentials);
        }
    }

    @Async
    public void saveLoginHistory(String userName, HttpServletRequest request) {
        Account account = accountService.getAccount(userName);
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setId(RandomUtils.getUUID());
        loginHistory.setPhone(account.getPhone());
        loginHistory.setMwId(account.getMwId());
        loginHistory.setLoginTime(new Date().getTime());
        Map<String, String> map = ClientUtil.getOsAndBrowserInfo(request);
        loginHistory.setLoginEquipment(map.get("os"));
        loginHistory.setBrowser(map.get("browser"));
        String ip = IPUtil.getIpAddr(request);
        loginHistory.setIp(ip);
        if (ip != null && (ip.equals("127.0.0.1") || ip.equals("localhost"))) {
            loginHistory.setLoginAddress("本地");
        } else {
            Map<String, String> address = IPUtil.getAddress(ip);
            if (address != null) {
                loginHistory.setLoginAddress(address.get("regionName") + address.get("city"));
            }
        }
        loginHistoryService.addLoginHistory(loginHistory);
    }

    @Async
    public void clearLoginLimit(HttpServletRequest request) {
        String ip = IPUtil.getIpAddr(request);
        String requestUrl = request.getRequestURL().toString();
        String clearLimitKey = RedisPrefix.REQUEST_LIMIT + ip + requestUrl;
        redisService.del(clearLimitKey);
    }

    @RequestMapping(value = "/getVerificationCode")
    @ApiOperation(value = "获取验证码", httpMethod = "POST")
    public void getVerificationCode(HttpServletResponse response, Model model) throws IOException {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        String key = RedisPrefix.VERIFICATION_CODE + RandomUtils.getUUID();
        logger.info(captcha.getCode());
        redisService.set(key, captcha.getCode(), 1800);
        captcha.write(response.getOutputStream());
        response.addHeader("key", key);
        response.setHeader("Access-Control-Expose-Headers", "key");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    @OpLog(describe = "用户退出")
    @RequestMapping(value = "/loginOut.do")
    @ApiOperation(value = "用户退出", httpMethod = "POST")
    public Result<Boolean> loginOut() {
        Result<Boolean> result = new Result<>();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        logger.info("用户退出成功");
        return result.success();
    }
}
