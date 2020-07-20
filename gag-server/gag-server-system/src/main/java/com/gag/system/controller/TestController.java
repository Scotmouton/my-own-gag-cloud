package com.gag.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Description:
 * User: scot
 * Date: 2020-07-02
 * Time: 15:18
 */
@RestController
public class TestController {

    @GetMapping("info")
    public String test(){
        return "gag-server-system";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal){
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name){
        return "hello"+name;
    }
}
