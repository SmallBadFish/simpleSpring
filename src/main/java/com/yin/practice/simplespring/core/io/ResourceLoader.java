package com.yin.practice.simplespring.core.io;

import org.springframework.util.ResourceUtils;

/**
 * 加载资源文件的接口,比如从classpath或者文件系统资源中加载配置和文件
 *
 */
public interface ResourceLoader {

	/** classpath url的前缀信息,使用:"classpath:" */
	String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

	/**
	 * 返回指定资源的Resource对象
	 * 
	 * @param location
	 * @return
	 */
	Resource getResource(String location);

	/**
	 * 返回挡前类的类加载器
	 * 
	 * @return
	 */
	ClassLoader getClassLoader();
}
