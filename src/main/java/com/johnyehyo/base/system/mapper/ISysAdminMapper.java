package com.johnyehyo.base.system.mapper;

import com.johnyehyo.base.system.domain.AdminEntity;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
public interface ISysAdminMapper {
    AdminEntity getAdminByAccount(String account);
}
