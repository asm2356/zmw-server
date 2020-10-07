package com.iflytek.common.exception;

/**
 * @author AZhao
 */
public class DBException extends BaseException {
    private static final long serialVersionUID = -9112653964852409303L;
    public  DBException(int code, String msg) {
        super(code,msg);
    }
}
