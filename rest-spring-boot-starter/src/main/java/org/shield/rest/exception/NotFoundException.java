package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 请求的资源不存在
 *
 * @author zacksleo@gmail.com
 */
public class NotFoundException extends RestException {
    private static final long serialVersionUID = 5864675318414795460L;

    public NotFoundException() {
        super("请求的资源不存在", HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
