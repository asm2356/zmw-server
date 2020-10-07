package com.iflytek.config.service.common;

import com.iflytek.common.annotation.RequestLimit;
import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.exception.RequestLimitException;
import com.iflytek.common.utils.HttpContextUtils;
import com.iflytek.common.utils.IPUtil;
import com.iflytek.config.service.redis.RedisService;
import org.apache.lucene.index.DocIDMerger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author AZhao
 */
public class RequestLimitAspect {
    @Autowired
    private RedisService redisService;
    private Logger logger = LoggerFactory.getLogger(RequestLimitAspect.class);

    public void before(JoinPoint joinPoint) throws RequestLimitException {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        String requestUrl = request.getRequestURL().toString();
        String key = RedisPrefix.REQUEST_LIMIT + ip + requestUrl;
        logger.info(key);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequestLimit requestLimit = method.getAnnotation(RequestLimit.class);
        if (!redisService.hasKey(key)) {
            redisService.set(key, 1, requestLimit.time());
        } else {
            long count = Long.valueOf(String.valueOf(redisService.get(key))) + 1;
            if (count > requestLimit.count()) {
                long time = redisService.getExpire(key);
                throw new RequestLimitException(ResultCode.Request_Limit.getCode(), ResultCode.Request_Limit.getMessage(), time, requestLimit.count());
            } else {
                redisService.set(key, count, redisService.getExpire(key));
            }
            logger.info("当前次数" + count);
        }
    }
}
