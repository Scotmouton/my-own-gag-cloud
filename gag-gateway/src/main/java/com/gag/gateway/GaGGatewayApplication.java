package com.gag.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @EnaleZuulProxy 开启zuul网关服务
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class GaGGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaGGatewayApplication.class, args);
    }

}
