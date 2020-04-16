package com.johnyehyo.base.system.service;

import com.johnyehyo.base.system.domain.AdminEntity;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
public interface ISysAdminService {

    AdminEntity getAdmin(String account);

}
