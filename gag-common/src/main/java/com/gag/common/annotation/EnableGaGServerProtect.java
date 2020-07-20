package com.gag.common.annotation;

import com.gag.common.configure.GaGServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 15:07
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GaGServerProtectConfigure.class)
public @interface EnableGaGServerProtect {
}
