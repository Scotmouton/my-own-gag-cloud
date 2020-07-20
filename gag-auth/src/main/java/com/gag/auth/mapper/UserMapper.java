package com.gag.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gag.common.entity.system.SystemUser;

/**
 * Description:
 * User: scot
 * Date: 2020-07-14
 * Time: 10:29
 */
public interface UserMapper extends BaseMapper<SystemUser> {

    SystemUser findByName(String username);

}
