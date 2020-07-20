package com.gag.auth.handler;

import com.gag.common.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description:
 * User: scot
 * Date: 2020-07-02
 * Time: 10:42
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}
