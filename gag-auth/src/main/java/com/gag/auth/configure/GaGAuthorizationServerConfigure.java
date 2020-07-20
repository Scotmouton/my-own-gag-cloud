package com.gag.auth.configure;

import com.gag.auth.properties.GaGAuthProperties;
import com.gag.auth.properties.GaGClientsProperties;
import com.gag.auth.service.GaGUserDetailService;
import com.gag.auth.translator.GaGWebResponseExceptionTranslator;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Description:
 * 继承AuthorizationServerConfigurerAdapter适配器，使用@EnableAuthorizationServer注解标注，开启认证服务器相关配置
 * User: scot
 * Date: 2020-07-02
 * Time: 9:44
 */
@Configuration
@EnableAuthorizationServer
public class GaGAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private GaGUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GaGAuthProperties authProperties;

    @Autowired
    private GaGWebResponseExceptionTranslator translator;

    /**
     * 客户端从认证服务器获取令牌的时候，必须使用client_id为febs，client_secret为123456的标识来获取；
     * 该client_id支持password模式获取令牌，并且可以通过refresh_token来获取新的令牌；
     * 在获取client_id为febs的令牌的时候，scope只能指定为all，否则将获取失败；
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        GaGClientsProperties[] clientsArray = authProperties.getClients();
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if(ArrayUtils.isNotEmpty(clientsArray)){
            for(GaGClientsProperties client : clientsArray){
                if(StringUtils.isBlank(client.getClient())){
                    throw new Exception("client不能为空");
                }
                if(StringUtils.isBlank(client.getSecret())){
                    throw new Exception("secret不能为空");
                }
                String[] grantTypes =  StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(),",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());
            }
        }

    }

    /**
     * 指定tokenStore() 指定userDetailsService为GaGUserDetailService
     * 指定异常翻译器GaGWebResponseExceptionTranslator
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(translator);
    }

    /**
     * tokenStore使用的是RedisTokenStore，认证服务器生成的令牌将被存储到Redis中。
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * defaultTokenServices指定了令牌的基本配置，比如令牌有效时间为60 * 60 * 24秒，
     * 刷新令牌有效时间为60 * 60 * 24 * 7秒，setSupportRefreshToken设置为true表示开启刷新令牌的支持。
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(authProperties.getAccessTokenValiditySeconds());
        tokenServices.setRefreshTokenValiditySeconds(authProperties.getRefreshTokenValiditySeconds());
        return tokenServices;
    }
}
