package com.sixman.roomus.product.command.domain.exception;

public class NullContentTypeException extends RuntimeException {
    public NullContentTypeException() {
        super();
    }

    public NullContentTypeException(String message) {
        super(message);
    }

    public NullContentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullContentTypeException(Throwable cause) {
        super(cause);
    }

    protected NullContentTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
