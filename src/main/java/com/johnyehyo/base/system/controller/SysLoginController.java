package com.johnyehyo.base.system.controller;

import com.johnyehyo.base.common.constant.Constants;
import com.johnyehyo.base.framework.session.SessionProvider;
import com.johnyehyo.base.framework.web.domain.ResponseEntity;
import com.johnyehyo.base.system.domain.LoginEntity;
import com.johnyehyo.base.system.service.ISysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author JohnYehyo
 * @date 2020-3-13
 */
@RestController
public class SysLoginController {

    @Autowired
    private ISysLoginService sysLoginService;
    @Autowired
    private SessionProvider sessionProvider;


    @RequestMapping(value = "sayHello")
    public String sayHello() {
        return "{\"success\": \"Hello\"}";
    }


    @PostMapping(value = "login")
    public ResponseEntity login(@RequestBody LoginEntity loginEntity, HttpServletRequest request) {

        ResponseEntity responseEntity = ResponseEntity.success("登录成功");
        //验证登录信息
        //todo 以后改为shiro控制
        boolean result = sysLoginService.login(loginEntity);
        if(!result){
            responseEntity = ResponseEntity.error("用户名或密码错误");
            return responseEntity;
        }
        // 生成令牌
        String token = sysLoginService.createToken(loginEntity.getAccount(), loginEntity.getPassword(), loginEntity.getCode(),
                loginEntity.getUuid());
        responseEntity.put(Constants.TOKEN, token);

        //暂时简单存储到HttpSession
        sessionProvider.setAttribute(request, token, loginEntity);
        return responseEntity;
    }
}
