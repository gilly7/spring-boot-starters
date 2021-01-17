package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 错误的请求
 *
 * @author liuyang, zacksleo <zacksleo@gmail.com>
 */
public class ErrorRequestException extends RestException {

    private static final long serialVersionUID = -3716409288887715224L;

    public ErrorRequestException() {
        super("错误的请求", HttpStatus.BAD_REQUEST);
    }

    public ErrorRequestException(String message) {
        super(message);
    }

    public ErrorRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorRequestException(Throwable cause) {
        super(cause);
    }

    public ErrorRequestException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
