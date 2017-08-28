package com.grain.base.exception;

/**
 * 订单异常基类
 * Created by yuchen
 * on 2015/6/12.
 */
public class OrderException extends DataException {
    public OrderException() {
    }

    public OrderException(String message) {
        super(message);
    }
}
