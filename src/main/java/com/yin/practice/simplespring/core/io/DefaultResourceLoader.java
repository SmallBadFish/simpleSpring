package com.yin.practice.simplespring.core.io;

import java.io.IOException;
import java.net.URL;

import org.springframework.core.io.UrlResource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public class DefaultResourceLoader implements ResourceLoader {
	@Override
	public Resource getResource(String location) {
		return null;
	}

	@Override
	public ClassLoader getClassLoader() {
		return null;
	}

//	private ClassLoader classLoader;
//
//	public DefaultResourceLoader() {
//		this.classLoader = ClassUtils.getDefaultClassLoader();
//	}
//
//	public DefaultResourceLoader(ClassLoader classLoader) {
//		this.classLoader = classLoader;
//	}
//
//	public void setClassLoader(ClassLoader classLoader) {
//		this.classLoader = classLoader;
//	}
//
//	public Resource getResource(String location) {
//		Assert.notNull(location, "Location must not be null");
//		if (location.startsWith("/")) {
//			return getResourceByPath(location);
//		} else if (location.startsWith(CLASSPATH_URL_PREFIX)) {
//			return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()), getClassLoader());
//		} else {
//			try {
//				URL url = new Url(location);
//				return new UrlResource(url);
//			} catch (MalformedUrlException ex) {
//				return getResourceBypath(location);
//			}
//		}
//	}
//
//	protected Resource getResourceByPath(String path) {
//		return new ClassPathContextResource(path, getClassLoader());
//	}
//
//	public ClassLoader getClassLoader() {
//		return (this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader());
//	}
//
//	protected static class ClassPathContextResource extends ClassPathResource implements ContextResource {
//		public ClassPathContextResource(String path, ClassLoader classLoader) {
//			super(path, classLoader);
//		}
//
//		public String getPathWithContext() {
//			return getPath();
//		}
//
//		public Resource createRelative(String relativePath) throws IOException {
//			String pathToUser = StringUtils.applyRelativePath(getPath(), relativePath);
//			return new ClassPathContextResource(pathToUser, getClassLoader());
//		}
//	}

}
