package com.common.exception;

/**
 * Created by Administrator on 2018/8/28.
 */
public class ParmException extends RuntimeException {

    public ParmException() {
        super();
    }

    public ParmException(String message) {
        super(message);
    }

    public ParmException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParmException(Throwable cause) {
        super(cause);
    }

    protected ParmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
