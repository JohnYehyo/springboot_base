package com.johnyehyo.base.system.service.impl;

import com.johnyehyo.base.common.utils.JWTUtil;
import com.johnyehyo.base.common.utils.MD5Util;
import com.johnyehyo.base.system.domain.LoginEntity;
import com.johnyehyo.base.system.domain.LoginUserEntity;
import com.johnyehyo.base.system.mapper.ISysLoginMapper;
import com.johnyehyo.base.system.service.ISysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JohnYehyo
 * @date 2020-4-13
 */
@Service
public class SysLoginServiceImpl implements ISysLoginService {

    @Autowired
    private ISysLoginMapper sysLoginMapper;

    /**
     * 验证登录
     * @param loginEntity
     * @return
     */
    @Override
    public boolean login(LoginEntity loginEntity) {
        loginEntity.setPassword(MD5Util.MD5Encode(loginEntity.getPassword(), "utf-8"));
        LoginUserEntity loginUser = sysLoginMapper.selectAdmin(loginEntity);
        if(null == loginUser){
            return false;
        }
        return true;
    }

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public String createToken(String username, String password) {
        String sign = JWTUtil.sign(username, password);
        return sign;
    }
}
