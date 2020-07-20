package com.gag.auth.service;

import com.gag.auth.manager.UserManager;
import com.gag.common.entity.GaGAuthUser;
import com.gag.common.entity.system.SystemUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: scot
 * Date: 2020-07-01
 * Time: 17:21
 */
@Service
public class GaGUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserManager userManager;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SystemUser systemUser = userManager.findByName(s);
        if (systemUser != null) {
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
                notLocked = true;
            GaGAuthUser authUser = new GaGAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            BeanUtils.copyProperties(systemUser,authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
