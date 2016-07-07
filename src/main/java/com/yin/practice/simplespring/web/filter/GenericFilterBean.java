package com.yin.practice.simplespring.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import com.yin.practice.simplespring.beans.DisposableBean;
import com.yin.practice.simplespring.beans.InitializingBean;
import com.yin.practice.simplespring.beans.factory.BeanNameAware;
import com.yin.practice.simplespring.context.EnvironmentAware;

public class GenericFilterBean implements Filter, BeanNameAware, EnvironmentAware, InitializingBean, DisposableBean {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private final Set<String> requiredProperties = new HashSet<String>();
	
	private FilterConfig filterConfig;
	
	private Environment environment = new StandardServletEnvironment();
	
	private ServletContext servletContext;
	
	private String beanName;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		Assert.notNull(filterConfig, "FilterConfig must not be null");
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing filter '" + filterConfig.getFilterName() + "'");
		}
		this.filterConfig = filterConfig;
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
