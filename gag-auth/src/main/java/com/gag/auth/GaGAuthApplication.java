package com.gag.auth;



import com.gag.common.annotation.EnableGaGAuthExceptionHandler;
import com.gag.common.annotation.EnableGaGLettuceRedis;
import com.gag.common.annotation.GaGCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@GaGCloudApplication
@EnableGaGLettuceRedis
@EnableGaGAuthExceptionHandler
@MapperScan("com.gag.auth.mapper")
public class GaGAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaGAuthApplication.class, args);
    }

}
