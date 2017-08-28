package com.grain.base.exception;

/**
 * 办理人过多
 * Created by yuchen
 * on 2015/6/12.
 */
public class TooManyTaskUserException extends DataException {

    public TooManyTaskUserException() {
    }

    public TooManyTaskUserException(String message) {
        super(message);
    }
}
