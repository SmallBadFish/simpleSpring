package com.yin.practice.simplespring.beans.factory;

import org.springframework.beans.factory.BeanFactory;

/**
 * BeanFactory的子接口,主要定义了实现层级关系的一些方法
 */
public interface HierarchicalBeanFactory extends BeanFactory {
	/**
	 * 获取父BeanFactory
	 * @return
	 */
	BeanFactory getParentBeanFactory();
	
	/**
	 * 当前beanFactory是否包含名称为name的bean
	 * @param name
	 * @return
	 */
	boolean containsLocalBean(String name);
}
