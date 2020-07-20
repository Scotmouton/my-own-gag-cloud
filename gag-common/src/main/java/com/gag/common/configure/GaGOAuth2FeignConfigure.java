package com.gag.common.configure;

import com.gag.common.entity.GaGConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;


/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 11:40
 */
public class GaGOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return requestTemplate -> {
            // 添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(GaGConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(GaGConstant.ZUUL_TOKEN_HEADER, zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if(details instanceof OAuth2AuthenticationDetails){
                String authorizationToken = ((OAuth2AuthenticationDetails)details).getTokenValue();
                requestTemplate.header("Authorization","bearer"+authorizationToken);
            }
        };
    }
}
