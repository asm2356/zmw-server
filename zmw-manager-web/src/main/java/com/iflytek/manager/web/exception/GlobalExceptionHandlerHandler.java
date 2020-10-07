package com.iflytek.manager.web.exception;

import com.iflytek.common.exception.WebExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author AZhao
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandlerHandler extends WebExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerHandler.class);
}