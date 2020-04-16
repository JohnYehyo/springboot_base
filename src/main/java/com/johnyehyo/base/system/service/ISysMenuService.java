package com.johnyehyo.base.system.service;

import com.johnyehyo.base.system.domain.LoginEntity;

/**
 * @author JohnYehyo
 * @date 2020-4-13
 */
public interface ISysMenuService {


    String getMenuByRoles(String role_ids);
}
