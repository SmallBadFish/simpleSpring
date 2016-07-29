package com.yin.practice.simplespring.core.env;

import org.springframework.core.env.PropertyResolver;

/**
 *	该接口表示了当前程序正在运行的环境
 *
 */
public interface Environment extends PropertyResolver{
	
	String[] getActiveProfiles();
	
	String[] getDefaultProfiles();
	
	boolean acceptsProfiles(String... profiles);
}
