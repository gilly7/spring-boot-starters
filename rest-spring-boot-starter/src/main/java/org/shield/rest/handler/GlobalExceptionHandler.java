package org.shield.rest.handler;

import org.shield.rest.exception.RestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * @author zacksleo@gmail.com
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${server.error.include-stacktrace:never}")
    private String includeStacktrace;

    final private String ALWAYS = "ALWAYS";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Object handler(BindException e) {
        return bindingResultHandler(e.getBindingResult(), getTrace(e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handler(MethodArgumentNotValidException e) {
        return bindingResultHandler(e.getBindingResult(), getTrace(e));
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<Object> handler(RestException e) {
        Map<String, Object> response = new HashMap<>(5);
        response.put("timestamp", new Date());
        response.put("status", e.getStatus());
        response.put("error", e.getClass().getSimpleName());
        response.put("message", e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Object handler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> errs = e.getConstraintViolations();
        Map<String, Object> response = new HashMap<>(5);
        response.put("timestamp", new Date());
        response.put("status", 400);
        response.put("error", "Bad Request");
        Map<String, String> errors = new HashMap<>(errs.size());
        for (ConstraintViolation<?> err : errs) {
            if (errors.isEmpty()) {
                response.put("message", err.getMessage());
            }
            if (err != null) {
                errors.put(err.getPropertyPath().toString(), err.getMessage());
            }
        }
        response.put("errors", errors);
        if (ALWAYS.equals(includeStacktrace)) {
            response.put("trace", getTrace(e));
        }
        return response;
    }

    public Object bindingResultHandler(BindingResult result, String trace) {
        List<ObjectError> errs = result.getAllErrors();
        Map<String, Object> response = new HashMap<>(5);
        response.put("timestamp", new Date());
        response.put("status", 400);
        response.put("error", "Bad Request");
        Map<String, String> errors = new HashMap<>(errs.size());
        for (ObjectError err : errs) {
            if (errors.isEmpty()) {
                response.put("message", err.getDefaultMessage());
            }
            if (err instanceof FieldError) {
                FieldError fieldError = (FieldError) err;
                errors.put(fieldError.getField(), err.getDefaultMessage());
            }
        }
        response.put("errors", errors);
        if (ALWAYS.equals(includeStacktrace)) {
            response.put("trace", trace);
        }
        return response;
    }

    private String getTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
