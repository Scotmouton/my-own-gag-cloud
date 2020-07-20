package com.gag.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gag.common.entity.system.Menu;

import java.util.List;

/**
 * Description:
 * User: scot
 * Date: 2020-07-14
 * Time: 10:30
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findUserPermissions(String username);


}
