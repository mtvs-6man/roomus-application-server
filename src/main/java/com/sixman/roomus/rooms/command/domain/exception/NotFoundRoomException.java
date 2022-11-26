package com.sixman.roomus.rooms.command.domain.exception;

public class NotFoundRoomException extends RuntimeException{
    public NotFoundRoomException() {
        super();
    }

    public NotFoundRoomException(String message) {
        super(message);
    }

    public NotFoundRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundRoomException(Throwable cause) {
        super(cause);
    }

    protected NotFoundRoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
