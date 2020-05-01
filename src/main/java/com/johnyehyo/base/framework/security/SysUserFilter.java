package com.johnyehyo.base.framework.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author JohnYehyo
 * @date 2020-5-1
 */
public class SysUserFilter extends PathMatchingFilter {


    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        Object object = SecurityUtils.getSubject().getPrincipal();
        return true;
    }
}
