package com.grain.base.exception;

/**
 * 未找到下一登录人
 * 未登录
 * Created by yuchen
 * on 2015/6/12.
 */
public class NotFoundTaskUserException extends DataException {

    public NotFoundTaskUserException() {
    }

    public NotFoundTaskUserException(String message) {
        super(message);
    }
}
