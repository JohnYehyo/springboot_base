package com.johnyehyo.base.system.service.impl;

import com.johnyehyo.base.system.domain.AdminEntity;
import com.johnyehyo.base.system.mapper.ISysAdminMapper;
import com.johnyehyo.base.system.service.ISysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
@Service
public class SysAdminServiceImpl implements ISysAdminService {
    @Autowired
    private ISysAdminMapper sysAdminMapper;

    @Override
    public AdminEntity getAdmin(String account) {
        AdminEntity adminEntity = sysAdminMapper.getAdminByAccount(account);
        return adminEntity;
    }
}
