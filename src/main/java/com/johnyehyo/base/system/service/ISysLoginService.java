package com.johnyehyo.base.system.service;

import com.johnyehyo.base.system.domain.LoginEntity;

/**
 * @author JohnYehyo
 * @date 2020-4-13
 */
public interface ISysLoginService {

    /**
     * 验证登录信息
     * @param loginEntity
     * @return
     */
    boolean login(LoginEntity loginEntity);

    /**
     * 生成token
     * @param account
     * @param password
     * @param code
     * @param uuid
     * @return
     */
    String createToken(String account, String password, String code, String uuid);


}
