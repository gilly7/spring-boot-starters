package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 上传的文件无法处理
 *
 * @author zacksleo@gmail.com
 */
public class UnprocessableEntityException extends RestException {
    private static final long serialVersionUID = 5864675318414795460L;

    public UnprocessableEntityException() {
        super("上传的文件无法处理", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public UnprocessableEntityException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
