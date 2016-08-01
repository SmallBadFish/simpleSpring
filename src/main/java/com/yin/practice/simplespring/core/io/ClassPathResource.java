package com.yin.practice.simplespring.core.io;

import java.io.IOException;
import java.io.InputStream;

import com.yin.practice.simplespring.util.Assert;
import com.yin.practice.simplespring.util.ClassUtils;
import com.yin.practice.simplespring.util.StringUtils;

public class ClassPathResource extends AbstractFileResolvingResource{
	private String path;
	private Object classLoader;
	private Class<?> clazz;

	/**
	 * Create a new {@code ClassPathResource} for {@code ClassLoader} usage. A
	 * leading slash will be removed, as the ClassLoader resource access methods
	 * will not accept it.
	 * 
	 * @param path
	 *            the absolute path within the classpath
	 * @param classLoader
	 *            the class loader to load the resource with, or {@code null}
	 *            for the thread context class loader
	 * @see ClassLoader#getResourceAsStream(String)
	 */
	public ClassPathResource(String path, ClassLoader classLoader) {
		Assert.notNull(path, "Path must not be null");
		String pathToUse = StringUtils.cleanPath(path);
		if (pathToUse.startsWith("/")) {
			pathToUse = pathToUse.substring(1);
		}
		this.path = pathToUse;
		this.classLoader = (classLoader != null ? classLoader : ClassUtils
				.getDefaultClassLoader());
	}

	/**
	 * Create a new {@code ClassPathResource} for {@code Class} usage. The path
	 * can be relative to the given class, or absolute within the classpath via
	 * a leading slash.
	 * 
	 * @param path
	 *            relative or absolute path within the class path
	 * @param clazz
	 *            the class to load resources with
	 * @see java.lang.Class#getResourceAsStream
	 */
	public ClassPathResource(String path, Class<?> clazz) {
		Assert.notNull(path, "Path must not be null");
		this.path = StringUtils.cleanPath(path);
		this.clazz = clazz;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
