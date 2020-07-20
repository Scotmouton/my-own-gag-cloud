package com.gag.gateway.filter;

import com.gag.common.entity.GaGConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 14:44
 */
@Slf4j
@Component
public class GaGGatewayRequestFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        String serviceId = (String)context.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = context.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        log.info("请求URI：{}，HTTP method:{}:请求IP:{},SERVICE_ID:{}",uri,method,host,serviceId);

        byte[] token = Base64Utils.encode((GaGConstant.ZUUL_TOKEN_VALUE).getBytes());
        context.addZuulRequestHeader(GaGConstant.ZUUL_TOKEN_HEADER,new String(token));
        return null;
    }
}
