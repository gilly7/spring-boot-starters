package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 认证失败
 *
 * @author zacksleo@gmail.com
 */
public class UnauthorizedException extends RestException {
    private static final long serialVersionUID = 5864675318414795460L;

    public UnauthorizedException() {
        super("认证失败，请重新登录", HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
