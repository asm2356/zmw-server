package com.iflytek.common.exception;

import org.csource.common.MyException;
/**
 * @author AZhao
 */
public class UploadFileException extends MyException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UploadFileException() {
    }

    public UploadFileException(String message) {
        super(message);
    }


}
