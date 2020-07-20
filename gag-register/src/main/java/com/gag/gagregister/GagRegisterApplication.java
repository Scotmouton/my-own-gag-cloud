package com.gag.gagregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GagRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GagRegisterApplication.class, args);
    }

}
