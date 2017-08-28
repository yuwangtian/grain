package com.grain.base.exception;

/**
 * 登录超时异常
 * 未登录
 * Created by yuchen
 * on 2015/6/12.
 */
public class NotFoundLoginUserException extends DataException {

    public NotFoundLoginUserException() {
    }

    public NotFoundLoginUserException(String message) {
        super(message);
    }
}
