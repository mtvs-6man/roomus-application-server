package com.sixman.roomus.commons.exception;

public class MoneyCanNotNagativeNumberException extends RuntimeException{
    public MoneyCanNotNagativeNumberException() {
        super();
    }

    public MoneyCanNotNagativeNumberException(String message) {
        super(message);
    }

    public MoneyCanNotNagativeNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoneyCanNotNagativeNumberException(Throwable cause) {
        super(cause);
    }

    protected MoneyCanNotNagativeNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
