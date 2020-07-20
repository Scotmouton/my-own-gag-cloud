package com.gag.common.handler;

import com.gag.common.entity.GaGResponse;
import com.gag.common.entity.constant.StringConstant;
import com.gag.common.exception.GaGException;
import com.gag.common.util.GaGUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * User: scot
 * Date: 2020-07-02
 * Time: 10:32
 */
@Slf4j
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GaGResponse handleException(Exception e) {
        String message = GaGUtil.containChinese(e.getMessage()) ? e.getMessage() : "系统内部异常";
        log.error(message, e);
        return new GaGResponse().message(message);
    }

    @ExceptionHandler(value = GaGException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GaGResponse handleFebsException(GaGException e) {
        log.error("FEBS系统异常", e);
        return new GaGResponse().message(e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return FebsResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GaGResponse handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(StringConstant.COMMA);
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString());
        return new GaGResponse().message(message.toString());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GaGResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(StringConstant.COMMA);
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString());
        return new GaGResponse().message(message.toString());
    }

    /**
     * 统一处理请求参数校验(json)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GaGResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(StringConstant.COMMA);
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString());
        return new GaGResponse().message(message.toString());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public GaGResponse handleAccessDeniedException() {
        return new GaGResponse().message("没有权限访问该资源");
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GaGResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        String message = "该方法不支持" + StringUtils.substringBetween(e.getMessage(), "'", "'") + "媒体类型";
        log.error(message);
        return new GaGResponse().message(message);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GaGResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = "该方法不支持" + StringUtils.substringBetween(e.getMessage(), "'", "'") + "请求方法";
        log.error(message);
        return new GaGResponse().message(message);
    }
}
