package com.gag.server.test.service;

import com.gag.common.entity.GaGServerConstant;
import com.gag.server.test.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * User: scot
 * Date: 2020-07-03
 * Time: 11:24
 */
@FeignClient(value = GaGServerConstant.GaG_SERVER_SYSTEM,contextId = "helloServiceClient"
        ,fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {
    @GetMapping("hello")
    String hello(@RequestParam String name);
}
