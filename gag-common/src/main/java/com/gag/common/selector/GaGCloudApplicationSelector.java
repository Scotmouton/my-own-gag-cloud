package com.gag.common.selector;

import com.gag.common.configure.GaGAuthExceptionConfigure;
import com.gag.common.configure.GaGOAuth2FeignConfigure;
import com.gag.common.configure.GaGServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Description:在Spring中，要将多个类进行注册，可以使用selector的方式
 * GaGAuthExceptionConfigure
 * GaGOAuth2FeignConfigure
 * GaGServerProtectConfigure
 * 三个功能都是微服务提供者必备的功能，所以我们可以定义一个注解将这三个功能整合在一起
 * 将这三个配置类一次性都注册到IOC容器中
 * User: scot
 * Date: 2020-07-03
 * Time: 15:15
 */
public class GaGCloudApplicationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                GaGAuthExceptionConfigure.class.getName(),
                GaGOAuth2FeignConfigure.class.getName(),
                GaGServerProtectConfigure.class.getName()
        };
    }
}
