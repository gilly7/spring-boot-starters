package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 请求的资源不存在
 *
 * @author zacksleo@gmail.com
 */
public class BadRequestException extends RestException {
    private static final long serialVersionUID = 5864675318414795460L;

    public BadRequestException() {
        super("验证失败", HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
