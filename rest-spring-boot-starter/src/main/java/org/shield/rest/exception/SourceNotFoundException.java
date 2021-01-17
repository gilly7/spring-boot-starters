package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 请求的资源不存在
 *
 * @author liuyang, zacksleo
 */
public class SourceNotFoundException extends RestException {

    private static final long serialVersionUID = -4428845154858237155L;

    public SourceNotFoundException() {
        super("请求的资源不存在", HttpStatus.NOT_FOUND);
    }

    public SourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public SourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public SourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
