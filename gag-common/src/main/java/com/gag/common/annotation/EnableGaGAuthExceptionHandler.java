package com.gag.common.annotation;

import com.gag.common.configure.GaGAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 10:37
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GaGAuthExceptionConfigure.class)
public @interface EnableGaGAuthExceptionHandler {
}
