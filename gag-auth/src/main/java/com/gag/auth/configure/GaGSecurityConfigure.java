package com.gag.auth.configure;

import com.gag.auth.service.GaGUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description:开启和web相关的安全配置
 * 用于处理/oauth开头的请求，Spring Cloud OAuth内部定义的获取令牌，
 * 刷新令牌的请求地址都是以/oauth/开头的，也就是说GaGSecurityConfigure用于处理和令牌相关的请求
 * @Order 表示注册的过滤器链中的优先级，数值越低，优先级别越高
 * 关于 @Order(2) 这一注解
 * @see WebSecurityConfigurerAdapter 可以发现继承的类 @Order(100)
 * {@link GaGResourceServerConfigure}和{@link GaGSecurityConfigure}工作类似，都可以对请求过滤，
 * 但是{@link GaGResourceServerConfigure}对所有请求生效，{@link GaGSecurityConfigure}只对 '/oauth'开头的请求有效
 * ‘/oauth/’ 开头的请求由{@link GaGSecurityConfigure}过滤器链处理，
 * 剩下的其他请求由{@link GaGResourceServerConfigure}过滤器链处理
 * 所以{@link GaGSecurityConfigure}的优先级应该高于{@link GaGResourceServerConfigure}
 * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration
 * 可以发现{@code order}为 3 所以这里设置为 2
 * User: scot
 * Date: 2020-07-01
 * Time: 17:17
 */
@Order(2)
@EnableWebSecurity
public class GaGSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private GaGUserDetailService userDetailService;

    /**
     * 返回BCryptPasswordEncoder {@link PasswordEncoder} spring security内部实现的加密方法也可以自己实现
     * 对于一个相同的密码，每次加密出来的加密串都不同
     * 密码校验时有使用内部实现的matches方法
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注册一个authenticationManagerBean
     * @see WebSecurityConfigurerAdapter
     * 密码模式需要使用到这个Bean
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     *  requestMatchers().antMatchers("/oauth/**")的含义是：
     *  FebsSecurityConfigure安全配置类只对/oauth/开头的请求有效
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();
    }

    /**
     * 指定userDetailsService和passwordEncoder
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
}
