package com.yin.practice.simplespring.core;

/**
 * 该接口提供了一系列属性操作的方法,对于属性值可以是任意的对象
 *
 */
public interface AttributeAccessor {
	
	void setAttribute(String name, Object value);
	
	Object getAttribute(String name);
	
	Object removeAttribute(String name);
	
	boolean hasAttribute(String name);
	
	String[] attributeNames();
}
