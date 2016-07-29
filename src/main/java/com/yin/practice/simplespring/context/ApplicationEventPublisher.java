package com.yin.practice.simplespring.context;

import org.springframework.context.ApplicationEvent;

/**
 * 该接口封装了ApplicationEvent事件的发布功能
 *
 */
public interface ApplicationEventPublisher {
	
	void publishEvent(ApplicationEvent event);
}
