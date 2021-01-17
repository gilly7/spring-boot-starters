package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录 @see WebMvcConfigurer
 *
 * @author liuyang, zacksleo@gmail.com
 */
public class ServiceException extends RestException {

    private static final long serialVersionUID = -5915013152260628333L;

    public ServiceException() {
        super("业务处理失败", HttpStatus.BAD_REQUEST);
    }

    public ServiceException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
