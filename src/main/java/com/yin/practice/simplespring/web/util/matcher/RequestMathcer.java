package com.yin.practice.simplespring.web.util.matcher;

import javax.servlet.http.HttpServletRequest;

/**
 * �򵥵�URL·��ӳ�����
 *
 */
public interface RequestMathcer {
	
	/**
	 * �Ƿ��ܹ�ƥ�䵱ǰHttpServletRequest����
	 * @param request
	 * @return
	 */
	boolean matches(HttpServletRequest request);
}
