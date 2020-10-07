package com.iflytek.manager.web.aop;

import com.iflytek.common.annotation.OpLog;
import com.iflytek.common.constant.MethodNameConstant;
import com.iflytek.common.model.OpLogRecord;
import com.iflytek.common.utils.HttpContextUtils;
import com.iflytek.common.utils.IPUtil;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.search.api.OpLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author AZhao
 */
@Aspect
@Order(2)
@Component
public class LogAspect {
    @Autowired
    private OpLogService opLogService;

    //不保存参数的方法
    private static List<String> notSaveMethodList = new ArrayList<String>() {
        {
        }
    };

    @Before(value = "@annotation(com.iflytek.common.annotation.OpLog)")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        Subject subject = SecurityUtils.getSubject();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OpLog opLog = method.getAnnotation(OpLog.class);
        OpLogRecord opLogRecord = new OpLogRecord();
        opLogRecord.setId(RandomUtils.getUUID());
        Class clazz = joinPoint.getTarget().getClass();
        String methodName = clazz.getName() + "." + joinPoint.getSignature().getName();
        opLogRecord.setMethod(methodName);
        Object[] args = joinPoint.getArgs(); // 参数值
        String[] argNames = signature.getParameterNames(); // 参数名
        StringBuilder stringBuilder = new StringBuilder();
        String argStr = "";
        if (args != null && args.length > 0 && !notSaveMethodList.contains(methodName)) {
            for (int i = 0; i < args.length; i++) {
                //代理模式
                String str = MethodNameConstant.MANAGER_LOGIN;
                String pattern = str.substring(0, str.lastIndexOf(".") + 1) + "*"
                        + str.substring(str.lastIndexOf("."));
                if (Pattern.matches(pattern, methodName) && argNames[i].equals("pwd")) {
                    args[i] = "******";
                }
                stringBuilder.append(argNames[i]).append("=").append(args[i]).append("&");
            }
            argStr = stringBuilder.toString();
            argStr = argStr.substring(0, argStr.length() - 1);
        }
        opLogRecord.setInputParams(argStr);
        opLogRecord.setDescribe(opLog.describe());
        opLogRecord.setOpTime(new Date().getTime());
        if (subject.getPrincipal() != null) {
            opLogRecord.setMwId(subject.getPrincipal().toString());
        }
        opLogRecord.setRemoteIP(IPUtil.getIpAddr(request));
        opLogService.insertLog(opLogRecord);
    }
}
