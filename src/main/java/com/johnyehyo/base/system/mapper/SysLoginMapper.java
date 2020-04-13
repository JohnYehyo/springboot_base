package com.johnyehyo.base.system.mapper;

import com.johnyehyo.base.system.domain.LoginEntity;
import com.johnyehyo.base.system.domain.LoginUserEntity;

/**
 * @author JohnYehyo
 * @date 2020-4-13
 */
public interface SysLoginMapper {

    /**
     * 查询用户信息
     * @param loginEntity
     * @return
     */
    LoginUserEntity selectAdmin(LoginEntity loginEntity);
}
