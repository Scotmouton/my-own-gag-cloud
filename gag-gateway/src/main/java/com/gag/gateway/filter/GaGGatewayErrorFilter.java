package com.gag.gateway.filter;

import com.gag.common.entity.GaGResponse;
import com.gag.common.util.GaGUtil;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 10:52
 */
@Slf4j
@Component
public class GaGGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try{
            GaGResponse response = new GaGResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String)ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message)?errorCause:message;
            response = resolveExceptionMessage(message,serviceId,response);

            HttpServletResponse servletResponse = ctx.getResponse();
            GaGUtil.makeResponse(
                    servletResponse, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,response
            );
            log.error("zuul sendError:{}",response.getMessage());
        }catch (Exception e){
            log.error("zuul sendError",e);
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }


    private GaGResponse resolveExceptionMessage(String message,String serviceId,GaGResponse response){
        if(StringUtils.containsIgnoreCase(message,"time out")){
            return response.message("请求"+serviceId+"服务超时");
        }
        if(StringUtils.containsIgnoreCase(message,"forwarding error")){
            return response.message(serviceId+"服务不可用");
        }
        return response.message("Zuul请求"+serviceId+"服务异常");
    }
}
