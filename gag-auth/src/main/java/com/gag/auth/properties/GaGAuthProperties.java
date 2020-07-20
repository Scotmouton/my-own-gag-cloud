package com.gag.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Description:
 * 去除gag.auth前缀，剩下部分和GaGAuthProperties配置类属性名称对应上的话，就会被读取到GaGAuthProperties相应的属性中。
 * 数组形式的属性值使用[]加元素下标表示，具体可以参考properties文件的语法
 * User: scot
 * Date: 2020-07-02
 * Time: 16:16
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:gag-auth.properties"})
@ConfigurationProperties(prefix = "gag.auth")
public class GaGAuthProperties {
    private GaGClientsProperties[] clients ={};
    private int accessTokenValiditySeconds = 60*60*24;
    private int refreshTokenValiditySeconds = 60*60*24*7;

    // 免认证路径
    private String anonUrl;
    private GaGValidateCodeProperties code = new GaGValidateCodeProperties();
}
