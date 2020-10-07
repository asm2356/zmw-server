package com.iflytek.front.web.aop;

import com.iflytek.common.exception.RequestLimitException;
import com.iflytek.config.service.common.RequestLimitAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author AZhao
 */
@Aspect
@Order(1)
@Component
public class FrontRequestLimitAspect extends RequestLimitAspect {
    @Before(value = "@annotation(com.iflytek.common.annotation.RequestLimit)")
    public void before(JoinPoint joinPoint) throws RequestLimitException{
        super.before(joinPoint);
    }
}
