package com.yin.practice.simplespring.core.io;

import java.io.IOException;

/**
 * 资源格式解析接口.比如一个Ant类型的路径,解析成为一个Resource对象
 *
 */
public interface ResourcePatternResolver extends ResourceLoader {

	String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

	/**
	 * 解析给定的location pattern成为Resource数组对象
	 * 
	 * @param locationPattern
	 * @return
	 * @throws IOException
	 */
	Resource[] getResources(String locationPattern) throws IOException;
}
