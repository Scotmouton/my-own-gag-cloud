package com.gag.server.test;


import com.gag.common.annotation.GaGCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFeignClients
@GaGCloudApplication
public class GaGServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaGServerTestApplication.class, args);
    }

}
