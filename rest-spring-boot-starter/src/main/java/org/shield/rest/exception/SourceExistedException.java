package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 资源已经存在
 *
 * @author liuyang, zacksleo
 */
public class SourceExistedException extends RestException {

    private static final long serialVersionUID = 1313524737824093641L;

    public SourceExistedException() {
        super("资源已经存在", HttpStatus.BAD_REQUEST);
    }

    public SourceExistedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public SourceExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceExistedException(Throwable cause) {
        super(cause);
    }

    public SourceExistedException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
