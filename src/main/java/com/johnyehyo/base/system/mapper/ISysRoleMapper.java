package com.johnyehyo.base.system.mapper;

import com.johnyehyo.base.system.domain.RoleEntity;


/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
public interface ISysRoleMapper {
    RoleEntity getRole(Integer id);
}
