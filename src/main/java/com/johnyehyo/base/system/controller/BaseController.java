package com.johnyehyo.base.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author JohnYehyo
 * @date 2020-4-27
 */
public class BaseController {

    public Object getSessionValue(String key){
        Subject subject = SecurityUtils.getSubject();
        Object obj = subject.getSession().getAttribute(key);
        return obj;
    }

    public <T> Object getSessionValue(String key, T t){
        Subject subject = SecurityUtils.getSubject();
        t = (T)subject.getSession().getAttribute(key);
        return t;
    }

}
