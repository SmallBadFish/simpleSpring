package com.yin.practice.simplespring.util;

public class ClassUtils {

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader c1 = null;
		try {
			c1 = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {
			// Cannot access thread context ClassLoader - falling back...
		}
		if (c1 == null) {
			// No thread context class loader -> use class loader of this class.
			c1 = ClassUtils.class.getClassLoader();
			if (c1 == null) {
				// getClassLoader() returning null indicates the bootstrap
				// ClassLoader
				try {
					c1 = ClassLoader.getSystemClassLoader();
				} catch (Throwable ex) {
					// Cannot access system ClassLoader - oh well, maybe the
					// caller can live with null...
				}
			}
		}
		return c1;
	}
	
	public static void main(String[] args) {
		ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
		System.out.println(defaultClassLoader.toString());
	}

}
