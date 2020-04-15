package com.johnyehyo.base.framework.session.impl;

import com.johnyehyo.base.framework.session.SessionProvider;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Session提供类的实现类
 *
 * @author JohnYehyo
 */
@Component
public class HttpSessionProvider implements SessionProvider {



    @Override
    public void setAttribute(HttpServletRequest request, Object name, Object value) {
        //获取Session对象
        HttpSession session = request.getSession();

        //把用户id放到Session域中
        if(name!=null&&!"".equals(name)){
            session.setAttribute(String.valueOf(name), value);
        }
    }

    @Override
    public Object getAttribute(HttpServletRequest request, String name) {
        //获取Session对象
        HttpSession session = request.getSession();
        //判断之前的Session是否存在
        if (null != session) {
            return session.getAttribute(name);
        }
        return null;
    }
}
