package com.yin.practice.simplespring.beans;

import org.springframework.util.ObjectUtils;

import com.yin.practice.simplespring.core.NestedRuntimeException;

public abstract class BeansException extends NestedRuntimeException {

	public BeansException(String msg) {
		super(msg);
	}

	public BeansException(String msg, Throwable cause) {
		super(msg, cause);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof BeansException)) {
			return false;
		}

		BeansException otherBe = (BeansException) other;
		return (getMessage().equals(otherBe.getMessage())
				&& ObjectUtils.nullSafeEquals(getCause(), otherBe.getCause()));
	}

	public int hashCode() {
		return getMessage().hashCode();
	}
}
