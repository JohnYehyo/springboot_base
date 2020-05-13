package com.johnyehyo.base.system.controller;

import com.johnyehyo.base.common.constant.Constants;
import com.johnyehyo.base.framework.web.domain.ResponseEntity;
import com.johnyehyo.base.system.domain.AdminEntity;
import com.johnyehyo.base.system.service.ISysLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JohnYehyo
 * @date 2020-3-13
 */
@RestController
public class SysLoginController {

    @Autowired
    private ISysLoginService sysLoginService;


    @GetMapping(value = "index")
    public ResponseEntity sayHello() {
        Subject subject = SecurityUtils.getSubject();
        return tokenAction(subject);
    }


    @PostMapping(value = "login")
    @CrossOrigin
    public ResponseEntity login(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return tokenAction(subject);
        }

        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = "";
        if(exceptionClassName!=null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
            } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
                error = "登录失败次数过多,账号锁定10分钟";
            }else if (exceptionClassName != null) {
                error = "其他错误：" + exceptionClassName;
            }
        }
        ResponseEntity responseEntity = ResponseEntity.error(error);
        return responseEntity;
    }

    private ResponseEntity tokenAction(Subject subject) {
        Session session = subject.getSession();
        //暂时简单存储到HttpSession
        AdminEntity adminEntity = (AdminEntity) session.getAttribute(Constants.LOGIN_USER);
        String token = sysLoginService.createToken(adminEntity.getT_account(), adminEntity.getT_password());
        Map map = new HashMap();
        map.put(Constants.TOKEN, token);
        ResponseEntity responseEntity = ResponseEntity.success("登陆成功", map);
        session.setAttribute(Constants.TOKEN, token);
        return responseEntity;
    }

}
