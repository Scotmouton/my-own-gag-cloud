package com.gag.auth.properties;

import lombok.Data;

/**
 * Description:
 * User: scot
 * Date: 2020-07-02
 * Time: 16:14
 */
@Data
public class GaGClientsProperties {
    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
