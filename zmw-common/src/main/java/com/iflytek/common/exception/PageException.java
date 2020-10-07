package com.iflytek.common.exception;
/**
 * @author AZhao
 */
public class PageException extends DBException {
    private static final long serialVersionUID = 6298584810576515472L;

    public PageException(int code, String msg) {
        super(code, msg);
    }
}
