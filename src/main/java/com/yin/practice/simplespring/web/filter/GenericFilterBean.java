package com.yin.practice.simplespring.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.core.env.Environment;

import com.yin.practice.simplespring.beans.DisposableBean;
import com.yin.practice.simplespring.beans.InitializingBean;
import com.yin.practice.simplespring.beans.factory.BeanNameAware;
import com.yin.practice.simplespring.context.EnvironmentAware;

public class GenericFilterBean implements Filter, BeanNameAware, EnvironmentAware, InitializingBean, DisposableBean {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

	}

	public void destroy() {

	}

	public void setBeanName(String name) {

	}

	public void afterPropertiesSet() throws Exception {
		
	}

	public void setEnvironment(Environment environment) {
		
	}

}
