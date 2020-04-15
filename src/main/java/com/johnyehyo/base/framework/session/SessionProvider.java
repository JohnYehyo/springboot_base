package com.johnyehyo.base.framework.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session提供类
 * @author JohnYehyo
 *
 */
public interface SessionProvider {


	/**
	 * 网页登录信息存入缓存
	 * @param name
	 * @param value
	 */
	void setAttribute(HttpServletRequest request, Object name, Object value);

	/**
	 * 获取网页端缓存数据
	 * @param name
	 * @return
	 */
	Object getAttribute(HttpServletRequest request, String name);
}
