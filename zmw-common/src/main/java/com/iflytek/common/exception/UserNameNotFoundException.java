package com.iflytek.common.exception;

import com.iflytek.common.enumeration.sys.ResultCode;

/**
 * @author AZhao
 */
public class UserNameNotFoundException extends BaseException{
    public UserNameNotFoundException(){
        super(ResultCode.Unknown_User.getCode(),ResultCode.Unknown_User.getMessage());
    }
}
