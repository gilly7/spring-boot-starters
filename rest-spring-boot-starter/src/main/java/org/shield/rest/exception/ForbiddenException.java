package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 没有权限进行操作
 *
 * @author zacksleo@gmail.com
 */
public class ForbiddenException extends RestException {
    private static final long serialVersionUID = 5864675318414795460L;

    public ForbiddenException() {
        super("抱歉，您没有权限进行该操作", HttpStatus.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
