package com.yin.practice.simplespring.context;

import java.util.Locale;

import org.springframework.context.NoSuchMessageException;

/**
 * 处理消息的接口,支持参数化和国际化
 *
 */
public interface MessageSource {

	String getMessage(String code, Object[] args, String defaultMessage, Locale loacal);

	String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException;

	String getMessage(MessageSourceResolvable reslvable, Locale locale) throws NoSuchMessageException;
}
