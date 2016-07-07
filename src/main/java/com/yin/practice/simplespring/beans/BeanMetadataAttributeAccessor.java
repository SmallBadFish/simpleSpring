package com.yin.practice.simplespring.beans;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.BeanMetadataElement;
import org.springframework.core.AttributeAccessorSupport;

/**
 * �ö������ʹ��һ����ֵ�����洢һϵ��BeanMetadataAttribute����
 *
 */
@SuppressWarnings("serial")
public class BeanMetadataAttributeAccessor extends AttributeAccessorSupport implements BeanMetadataElement {

	private Object source;
	
	public Object getSource() {
		return this.source;
	}
	
	public void setSource(Object source) {
		this.source = source;
	}

	public void addMetadataAttribute(BeanMetadataAttribute attribute){
		super.setAttribute(attribute.getName(), attribute);
	}
	
	public BeanMetadataAttribute getMetadataAttribute(String name){
		return (BeanMetadataAttribute) super.getAttribute(name);
	}
	
	@Override
	public void setAttribute(String name, Object value) {
		super.setAttribute(name, new BeanMetadataAttribute(name, value));
	}
	
	@Override
	public Object getAttribute(String name) {
		BeanMetadataAttribute attribute = (BeanMetadataAttribute) super.getAttribute(name);
		return (attribute != null ? attribute.getValue() : null);
	}

	@Override
	public Object removeAttribute(String name) {
		BeanMetadataAttribute attribute = (BeanMetadataAttribute) super.removeAttribute(name);
		return (attribute != null ? attribute.getValue() : null);
	}

}
