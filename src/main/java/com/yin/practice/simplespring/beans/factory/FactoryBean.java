package com.yin.practice.simplespring.beans.factory;

/**
 * 如果一个bean实现了该接口,那么它被当做一个factory,而不是作为一个bean实例
 *
 * @param <T>
 */
public interface FactoryBean<T> {

	T getObject() throws Exception;

	Class<?> getObjectType();

	boolean isSingleton();
}
