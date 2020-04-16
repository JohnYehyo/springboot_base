package com.johnyehyo.base.system.service;

import java.util.Set;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
public interface ISysRoleService {

    Set<String> findPermissions(Integer[] stringToInt);
}
