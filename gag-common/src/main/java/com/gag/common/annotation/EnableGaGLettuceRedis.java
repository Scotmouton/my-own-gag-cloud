package com.gag.common.annotation;

import com.gag.common.configure.GaGLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Description:
 * User: scot
 * Date: 2020-07-16
 * Time: 8:47
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GaGLettuceRedisConfigure.class)
public @interface EnableGaGLettuceRedis {
}
