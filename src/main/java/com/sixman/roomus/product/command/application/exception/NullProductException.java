package com.sixman.roomus.product.command.application.exception;

public class NullProductException extends RuntimeException{
    public NullProductException() {
        super();
    }

    public NullProductException(String message) {
        super(message);
    }

    public NullProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullProductException(Throwable cause) {
        super(cause);
    }

    protected NullProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
