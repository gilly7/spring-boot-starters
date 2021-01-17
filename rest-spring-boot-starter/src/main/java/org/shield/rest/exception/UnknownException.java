package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 发生未知错误
 *
 * @author liuyang
 */
public class UnknownException extends RestException {

    private static final long serialVersionUID = 2596659924604204140L;

    public UnknownException() {
        super("发生未知错误", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public UnknownException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public UnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownException(Throwable cause) {
        super(cause);
    }

    public UnknownException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
