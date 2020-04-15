package com.johnyehyo.base.system.controller;

import com.johnyehyo.base.framework.session.SessionProvider;
import com.johnyehyo.base.framework.web.domain.ResponseEntity;
import com.johnyehyo.base.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JohnYehyo
 * @date 2020-4-15
 */
@RestController
@RequestMapping(value = "sys/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private SessionProvider sessionProvider;

    @GetMapping()
    public ResponseEntity menu(@RequestHeader("Authorization") String token, HttpServletRequest request){
        ResponseEntity responseEntity = ResponseEntity.success();
        sessionProvider.getAttribute(request, token);
//        sysMenuService.
//        AdminEntity adminEntity = adminService.getAdmin(username);
//        String json = tbadminService.getMenuJson2(adminEntity.getRole_ids());
        return responseEntity;
    }
}
