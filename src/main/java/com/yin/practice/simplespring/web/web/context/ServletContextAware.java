package com.yin.practice.simplespring.web.web.context;

import javax.servlet.ServletContext;

import com.yin.practice.simplespring.beans.factory.Aware;

public interface ServletContextAware extends Aware {
	
	void setServletContext(ServletContext servletContext);
	
}
