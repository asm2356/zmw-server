package com.iflytek.common.exception;

import org.csource.common.MyException;
/**
 * @author AZhao
 */
public class DeleteFileException extends MyException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DeleteFileException(String message) {
        this.message = message;
    }

}
