package com.yin.practice.simplespring.core.env;

/**
 * 该接口用来处理基础配置文件的配置信息
 *
 */
public interface PropertyResolver {

	boolean containsProperty(String key);

	String getProperty(String key);

	String getProperty(String key, String defaultValue);

	<T> T getProperty(String key, Class<T> targetType);

	<T> T getProperty(String key, Class<T> targetType, T defaultValue);

	<T> Class<T> getPropertyAsClass(String key, Class<T> targetType);

	String getRequiredProperty(String key) throws IllegalStateException;

	<T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException;

	String resolveRequiredPlaceholders(String text) throws IllegalArgumentException;

	String resolvePlaceholders(String text);
}
