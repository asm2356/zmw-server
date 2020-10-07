package com.iflytek.common.exception;

import org.csource.common.MyException;
/**
 * @author AZhao
 */
public class DownloadException  extends MyException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DownloadException(String message) {
        this.message = message;
    }


}
