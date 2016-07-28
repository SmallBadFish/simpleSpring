package com.yin.practice.simplespring.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * BeanFactoy是spring IOC容器的根接口,提供了IOC容器的基本功能
 *
 */
public interface BeanFactory {

	/**
	 * 使用"&"标识符来区分BeanFactory和BeanFactory创建的beans
	 */
	String FACTORY_BEAN_PREFIX = "&";

	/**
	 * 根据bean的名称获取bean
	 * 
	 * @param name
	 * @return
	 * @throws BeansException
	 */
	Object getBean(String name) throws BeansException;

	/**
	 * 根据bean的名称和具体类信息获取bean
	 * 
	 * @param name
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 */
	<T> T getBean(String name, Class<T> requiredType) throws BeansException;

	/**
	 * 根据类信息获取bean
	 * 
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 */
	<T> T getBean(Class<T> requiredType) throws BeansException;

	/**
	 * 
	 * @param name
	 * @param args
	 * @return
	 * @throws BeansException
	 */
	Object getBean(String name, Object... args) throws BeansException;

	/**
	 * 
	 * @param requiredType
	 * @param args
	 * @return
	 * @throws BeansException
	 */
	<T> T getBean(Class<T> requiredType, Object... args) throws BeansException;

	/**
	 * 根据bean的名称判断bean是否存在
	 * 
	 * @param name
	 * @return
	 */
	boolean containsBean(String name);

	/**
	 * 根据bean的名称判断bean是否是单例的
	 * 
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

	/**
	 * 根据bean的名称判断bean是否是原的
	 * 
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

	/**
	 * 根据bean的名称和类信息判断bean是否匹配的
	 * 
	 * @param name
	 * @param targetType
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException;

	/**
	 * 根据bena的名称获取bean的具体类信息
	 * 
	 * @param name
	 *            bean的名称
	 * @return bean的类信息
	 * @throws NoSuchBeanDefinitionException
	 */
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;

	/**
	 * 根据bean的名称返回所有的别名
	 * 
	 * @param name
	 * @return 所有的别名,如果没有返回空数组
	 */
	String[] getAliases(String name);
}
