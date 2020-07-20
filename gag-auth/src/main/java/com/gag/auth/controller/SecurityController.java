package com.gag.auth.controller;

import com.gag.auth.service.ValidateCodeService;
import com.gag.common.entity.GaGResponse;
import com.gag.common.exception.GaGException;
import com.gag.common.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * Description:
 * User: scot
 * Date: 2020-07-02
 * Time: 9:55
 */
@Slf4j
@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth(){
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal){
        return principal;
    }

    @DeleteMapping("signout")
    public GaGResponse signout(HttpServletRequest request) throws GaGException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        GaGResponse febsResponse = new GaGResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new GaGException("退出登录失败");
        }
        return febsResponse.message("退出登录成功");
    }



    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        log.info("进入获取captcha方法！");
        validateCodeService.create(request, response);
    }
}
