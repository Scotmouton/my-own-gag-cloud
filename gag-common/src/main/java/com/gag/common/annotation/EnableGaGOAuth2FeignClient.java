package com.gag.common.annotation;

import com.gag.common.configure.GaGOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 11:47
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(GaGOAuth2FeignConfigure.class)
public @interface EnableGaGOAuth2FeignClient {
}
