package com.yin.practice.simplespring.web.filter;

import org.springframework.core.env.Environment;

public class StandardServletEnvironment implements Environment {

	public boolean containsProperty(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getProperty(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProperty(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getProperty(String key, Class<T> targetType) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Class<T> getPropertyAsClass(String key, Class<T> targetType) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRequiredProperty(String key) throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	public String resolvePlaceholders(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getActiveProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getDefaultProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean acceptsProfiles(String... profiles) {
		// TODO Auto-generated method stub
		return false;
	}

}
