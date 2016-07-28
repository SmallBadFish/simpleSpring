package com.yin.practice.simplespring.web.util.matcher;

import javax.servlet.http.HttpServletRequest;

/**
 * 简单的URL路径映射策略
 *
 */
public interface RequestMathcer {
	
	/**
	 * 是否能够匹配当前HttpServletRequest对象
	 * @param request
	 * @return
	 */
	boolean matches(HttpServletRequest request);
}
