package com.johnyehyo.base.system.service.impl;

import com.johnyehyo.base.common.utils.JWTUtil;
import com.johnyehyo.base.common.utils.MD5Util;
import com.johnyehyo.base.system.domain.LoginEntity;
import com.johnyehyo.base.system.domain.LoginUserEntity;
import com.johnyehyo.base.system.mapper.ISysMenuMapper;
import com.johnyehyo.base.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JohnYehyo
 * @date 2020-4-13
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private ISysMenuMapper sysMenuMapper;


}
