package com.gag.system;


import com.gag.common.annotation.GaGCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @EnableGlobalMethodSecurity(prePostEnabled = true)表示开启spring cloud security权限注解
 * @EnableGaGAuthExceptionHandler			认证类型异常翻译
 * @EnableGaGOAuth2FeignClient				开启带令牌的Feign请求，避免微服务内部调用出现401异常
 * @EnableGaGServerProtect  				开启微服务防护，避免客户端绕过网关直接请求微服务
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@GaGCloudApplication
public class GaGServerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaGServerSystemApplication.class, args);
	}

}
