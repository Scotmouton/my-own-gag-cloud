package com.gag.common.annotation;

import com.gag.common.selector.GaGCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 15:14
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GaGCloudApplicationSelector.class)
public @interface GaGCloudApplication {
}
