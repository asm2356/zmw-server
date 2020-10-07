package com.iflytek.common.exception;


public class MyValidationException extends BaseException {
    public MyValidationException(int code, String msg) {
        super(code, msg);
    }
}
