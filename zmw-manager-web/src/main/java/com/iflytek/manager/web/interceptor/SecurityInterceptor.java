package com.iflytek.manager.web.interceptor;

import com.iflytek.common.utils.SecurityFilterUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AZhao 
 */
public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SecurityFilterUtils.isHasInvalidCharacter(request,response);
        return true;
    }
}
