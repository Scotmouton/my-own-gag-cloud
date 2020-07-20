package com.gag.common.handler;

import com.gag.common.entity.GaGResponse;
import com.gag.common.util.GaGUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:令牌不正确返回401
 * User: scot
 * Date: 2020-07-03
 * Time: 10:24
 */
public class GaGAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        GaGResponse response = new GaGResponse();
        GaGUtil.makeResponse(httpServletResponse, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED,response.message("token无效"));

    }
}
