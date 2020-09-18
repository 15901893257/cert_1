package com.bupt.dql.web.exception;

/**
 * @author: mai
 * @date: 2020/9/14
 */
public class ParamException extends BaseException {
    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }
}
