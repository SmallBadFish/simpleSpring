package com.yin.practice.simplespring.core;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.AttributeAccessor;
import org.springframework.util.Assert;

/**
 * 抽象类实现了接口 AttributeAccessor,声明一个Map对象用来实现对属性的常用操作,比如赋值,获取,移除等操作
 *
 */
@SuppressWarnings("serial")
public abstract class AttributeAccessorSupport implements AttributeAccessor, Serializable {
	/* String类型为键 任意对象可为值得Map对象 */
	private final Map<String, Object> attributes = new LinkedHashMap<String, Object>();

	public void setAttribute(String name, Object value) {
		Assert.notNull(name, "Name must not be null");
		if (value != null) {
			this.attributes.put(name, value);
		} else {
			removeAttribute(name);
		}
	}

	public Object getAttribute(String name) {
		Assert.notNull(name, "Name must not be null");
		return this.attributes.get(name);
	}

	public Object removeAttribute(String name) {
		Assert.notNull(name, "Name must not be null");
		return this.attributes.remove(name);
	}

	public boolean hasAttribute(String name) {
		Assert.notNull(name, "Name must not be null");
		return this.attributes.containsKey(name);
	}

	public String[] attributeNames() {
		return this.attributes.keySet().toArray(new String[this.attributes.size()]);
	}

	protected void copyAttributesFrom(AttributeAccessor source) {
		Assert.notNull(source, "Source must not be null");
		String[] attributeNames = source.attributeNames();
		for (String attributeName : attributeNames) {
			setAttribute(attributeName, source.getAttribute(attributeName));
		}
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AttributeAccessorSupport)) {
			return false;
		}
		AttributeAccessorSupport that = (AttributeAccessorSupport) other;
		return this.attributes.equals(that.attributes);
	}

	@Override
	public int hashCode() {
		return this.attributes.hashCode();
	}

}
