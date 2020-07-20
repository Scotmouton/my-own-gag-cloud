package com.gag.common.configure;

import com.gag.common.handler.GaGAccessDeniedHandler;
import com.gag.common.handler.GaGAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 10:33
 */

public class GaGAuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public GaGAccessDeniedHandler accessDeniedHandler(){
        return new GaGAccessDeniedHandler();
    }


    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public GaGAuthExceptionEntryPoint authExceptionEntryPoint(){
        return new GaGAuthExceptionEntryPoint();
    }
}
