package com.gag.auth.configure;

import com.gag.auth.properties.GaGAuthProperties;
import com.gag.common.handler.GaGAccessDeniedHandler;
import com.gag.common.handler.GaGAuthExceptionEntryPoint;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Description:开启资源服务器相关配置
 * 用于处理非/oauth/开头的请求，其主要用于资源的保护，客户端只能通过OAuth2协议发放的令牌来从资源服务器中获取受保护的资源。
 * {@link @EnableResourceServer}
 * User: scot
 * Date: 2020-07-01
 * Time: 17:26
 */
@Configuration
@EnableResourceServer
public class GaGResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private GaGAuthExceptionEntryPoint authExceptionEntryPoint;

    @Autowired
    private GaGAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private GaGAuthProperties properties;

    /**
     * 重写configure(HttpSecurity http)方法，
     * 通过requestMatchers().antMatchers("/**")的配置表明该安全配置对所有请求都生效。
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
    }

    /**
     * 资源服务器异常处理：令牌不正确返回401和用户无权限返回403
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(authExceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
