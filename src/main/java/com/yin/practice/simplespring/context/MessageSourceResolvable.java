package com.yin.practice.simplespring.context;

/**
 * 消息来源处理类
 *
 */
public interface MessageSourceResolvable {
	
	String[] getCodes();

	Object[] getArguments();

	String getDefaultMessage();
}
