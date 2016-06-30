package com.yin.practice.simplespring.context;

import org.springframework.core.env.Environment;

/**
 * 
 *
 */
public interface EnvironmentAware {
	
	void setEnvironment(Environment environment);
	
}
