package org.shield.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * Restful风格的异常
 *
 * @author zacksleo@gmail.com
 */
public class RestException extends RuntimeException {

    private static final long serialVersionUID = 3672243364349588167L;

    /**
     * HTTP 状态码
     */
    private int status = 500;
    /**
     * 业务异常代码
     */
    private int code = 400;

    public RestException() {
    }

    public RestException(String message, int status) {
        super(message);
        this.status = status;
    }

    public RestException(String message, HttpStatus status) {
        super(message);
        this.status = status.value();
    }

    public RestException(String message, HttpStatus status, int code) {
        super(message);
        this.status = status.value();
        this.code = code;
    }

    public RestException(String message) {
        super(message);
        this.status = 500;
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(Throwable cause) {
        super(cause);
    }

    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static RestException exception(String message, HttpStatus status) {
        return new RestException(message, status);
    }

    /**
     * 资源不存在
     *
     * @param message
     * @return
     */
    public static RestException notFound(String message) {
        return exception(message, HttpStatus.NOT_FOUND);
    }

    /**
     * 不可以处理的实体，客户端上传的附件无法处理
     *
     * @param message
     * @return
     */
    public static RestException unprocessableEntity(String message) {
        return exception(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * 非法的请求, 验证失败
     *
     * @param message
     * @return
     */
    public static RestException badRequest(String message) {
        return exception(message, HttpStatus.BAD_REQUEST);
    }
}
