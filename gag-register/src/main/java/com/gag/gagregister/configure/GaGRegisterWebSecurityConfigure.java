package com.gag.gagregister.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * User: scot
 * Date: 2020-07-01
 * Time: 16:46
 */
@EnableWebSecurity
public class GaGRegisterWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
