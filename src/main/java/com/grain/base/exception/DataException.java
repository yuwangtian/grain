package com.grain.base.exception;

/**
 * 数据异常
 * Created by yuchen
 * on 2015/6/12.
 */
public class DataException extends Exception {
    protected String message;

    public DataException() {
    }

    public DataException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
