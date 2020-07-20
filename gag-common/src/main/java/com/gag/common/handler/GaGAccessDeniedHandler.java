package com.gag.common.handler;

import com.gag.common.entity.GaGResponse;
import com.gag.common.util.GaGUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:用户无权限返回403
 * User: scot
 * Date: 2020-07-03
 * Time: 10:31
 */

public class GaGAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        GaGResponse response = new GaGResponse();
        GaGUtil.makeResponse(
                httpServletResponse, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN,response.message("没有权限访问资源")
        );
    }
}
