package com.gag.gateway.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Description:
 * User: scot
 * Date: 2020-07-02
 * Time: 11:45
 */
@EnableWebSecurity
public class GaGGatewaySecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
