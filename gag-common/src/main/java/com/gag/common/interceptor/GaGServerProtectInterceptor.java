package com.gag.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.gag.common.entity.GaGConstant;
import com.gag.common.entity.GaGResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 14:52
 */

public class GaGServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 Zuul Token
        String token = request.getHeader(GaGConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode((GaGConstant.ZUUL_TOKEN_VALUE).getBytes()));
        // 校验 Zuul Token的正确性
        if(StringUtils.equals(token,zuulToken)){
            return true;
        }else{
            GaGResponse gagResponse = new GaGResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(gagResponse.message("请通过网关获取资源！")));
            return false;
        }
    }
}
