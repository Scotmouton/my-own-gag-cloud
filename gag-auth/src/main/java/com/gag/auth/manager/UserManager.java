package com.gag.auth.manager;

import com.gag.auth.mapper.MenuMapper;
import com.gag.auth.mapper.UserMapper;
import com.gag.common.entity.system.Menu;
import com.gag.common.entity.system.SystemUser;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * User: scot
 * Date: 2020-07-14
 * Time: 10:33
 */
@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username){
        return userMapper.findByName(username);
    }

    public String findUserPermissions(String username){
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);

        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}
