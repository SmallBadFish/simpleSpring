package com.yin.practice.simplespring.beans;

/**
 * 该接口定义了destroy()方法,当某些类在销毁时并且想要释放资源实现该接口。<br>
 * 比如一个BeanFactory在处理缓存中的单例类时应该调用destroy ()方法,<br>
 * 还有application context(应用程序上下文)在关闭时也应该处理它所有的单例类。
 *
 */
public interface DisposableBean {

	void destroy() throws Exception;

}
