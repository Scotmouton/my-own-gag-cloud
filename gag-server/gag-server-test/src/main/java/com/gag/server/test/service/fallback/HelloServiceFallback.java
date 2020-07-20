package com.gag.server.test.service.fallback;

import com.gag.common.entity.GaGServerConstant;
import com.gag.server.test.service.IHelloService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 11:28
 */
@Slf4j
@Component
public class HelloServiceFallback implements FallbackFactory<IHelloService> {

    @Override
    public IHelloService create(Throwable throwable) {
        return new IHelloService() {
            @Override
            public String hello(String name) {
                log.error("调用"+ GaGServerConstant.GaG_SERVER_SYSTEM+"服务出错",throwable);
                return "调用出错";
            }
        };
    }
}
