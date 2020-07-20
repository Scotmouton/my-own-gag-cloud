package com.gag.system.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Description:
 * 所有访问gag-server-system的请求都需要认证，只有通过认证服务器发放的令牌才能进行访问
 * User: scot
 * Date: 2020-07-02
 * Time: 15:12
 */
@Configuration
@EnableResourceServer
public class GaGServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
            .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }
}
