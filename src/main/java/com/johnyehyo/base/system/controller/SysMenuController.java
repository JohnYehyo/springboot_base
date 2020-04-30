package com.johnyehyo.base.system.controller;

import com.alibaba.fastjson.JSON;
import com.johnyehyo.base.common.constant.Constants;
import com.johnyehyo.base.framework.session.SessionProvider;
import com.johnyehyo.base.framework.web.domain.ResponseEntity;
import com.johnyehyo.base.system.domain.AdminEntity;
import com.johnyehyo.base.system.service.ISysAdminService;
import com.johnyehyo.base.system.service.ISysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author JohnYehyo
 * @date 2020-4-15
 */
@RestController
@RequestMapping(value = "sys/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService sysMenuService;

    @GetMapping(value = "menu")
    public ResponseEntity menu(){
//        System.out.println(token);
        AdminEntity adminEntity = (AdminEntity) getSessionValue(Constants.LOGIN_USER);
        String json = sysMenuService.getMenuByRoles(adminEntity.getRole_ids());
        ResponseEntity responseEntity = ResponseEntity.success("请求成功", JSON.parse(json));
        return responseEntity;
    }
}
