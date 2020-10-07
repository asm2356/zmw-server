package com.iflytek.common.exception;
/**
 * @author AZhao
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 3546894316054269771L;
    private int code;
    private String message;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                '}';
    }
}
