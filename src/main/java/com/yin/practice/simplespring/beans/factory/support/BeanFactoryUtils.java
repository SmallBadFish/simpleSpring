package com.yin.practice.simplespring.beans.factory.support;

import com.yin.practice.simplespring.beans.factory.BeanFactory;
import com.yin.practice.simplespring.util.Assert;

public abstract class BeanFactoryUtils {

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static String transformedBeanName(String name) {
		Assert.notNull(name, "'name' must not be null");
		String beanName = name;
		while (beanName.startsWith(BeanFactory.FACTORY_BEAN_PREFIX)) {
			beanName = beanName.substring(BeanFactory.FACTORY_BEAN_PREFIX.length());
		}
		return beanName;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isFactoryDereference(String name) {
		return (name != null && name.startsWith(BeanFactory.FACTORY_BEAN_PREFIX));
	}

}
