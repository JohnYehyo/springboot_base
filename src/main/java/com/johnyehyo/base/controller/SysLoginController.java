package com.johnyehyo.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JohnYehyo
 * @date 2020-3-13
 */
@RestController
@RequestMapping(value = "login")
public class SysLoginController {


    @RequestMapping(value = "sayHello")
    public String sayHello(){
        return "{\"success\": \"Hello\"}";
    }
}
