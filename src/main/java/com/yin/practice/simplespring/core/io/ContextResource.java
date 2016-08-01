package com.yin.practice.simplespring.core.io;

/**
 * 用于从封闭的上下文中加载资源文件,比如{@link javax.servlet.ServletContext}或者
 * {@link javax.portlet.PortletContext}
 *
 */
public interface ContextResource extends Resource {

	String getPathWithContext();
}
