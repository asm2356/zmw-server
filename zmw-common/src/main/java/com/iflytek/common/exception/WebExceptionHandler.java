package com.iflytek.common.exception;

import com.iflytek.common.enumeration.sys.ResultCode;
import com.iflytek.common.model.Result;
import com.iflytek.common.utils.MyUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

/**
 * @author AZhao
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(UploadFileException.class)
    public Result UploadFileException(UploadFileException e) {
        logger.error("上传异常", e);
        return new Result().failure(ResultCode.Upload_Failure);
    }

    @ExceptionHandler(DownloadException.class)
    public Result DownloadException(DownloadException e) {
        logger.error("下载异常", e);
        return new Result().failure(ResultCode.DownLoad_File_Failure);
    }

    @ExceptionHandler(DeleteFileException.class)
    public Result DeleteFileException(DeleteFileException e) {
        logger.error("删除文件异常", e);
        return new Result().failure(ResultCode.Delete_File_Failure);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("400 参数解析异常", e);
        return new Result().failure(ResultCode.Argument_Error);
    }


    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("405 不支持当前请求方法", e);
        return new Result().failure(ResultCode.Request_Method_Error);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error("500 服务运行异常", e);
        return new Result().failure(ResultCode.Service_Error);
    }

    @ExceptionHandler(RequestLimitException.class)
    public Result<Object> requestLimit(RequestLimitException e) {
        String errorMessage = "访问次数受限已达" +
                e.getCount() +
                "次，请" +
                MyUtils.secondToTime(e.getTime()) +
                "后再试";
        logger.info(errorMessage);
        return new Result<>().failure(ResultCode.Request_Limit.getCode(), errorMessage);
    }

    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public Result unLogin() {
        logger.error("未登录");
        return new Result().failure(ResultCode.UnLogin);
    }

    @ExceptionHandler({AuthorizationException.class, UnauthorizedException.class})
    public Result unAuthorization() {
        logger.error("未授权");
        return new Result().failure(ResultCode.No_Permission);
    }

    @ExceptionHandler(PageException.class)
    public Result pageException(PageException e) {
        logger.error("分页异常", e);
        return new Result().failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(OtherUserLoginException.class)
    public Result otherUserLoginException(OtherUserLoginException e) {
        logger.error("其他用户登录异常", e);
        return new Result().failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(UnsafeRequestException.class)
    public Result unsafeRequestException(UnsafeRequestException e) {
        logger.error("不安全请求", e);
        return new Result().failure(e.getCode(), e.getMessage());
    }
}
