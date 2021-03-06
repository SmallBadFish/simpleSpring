package com.yin.practice.simplespring.context.support;

import java.io.Serializable;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.yin.practice.simplespring.context.MessageSourceResolvable;

/**
 * MessageSourceResolvable消息处理接口的默认实现类
 *
 */
@SuppressWarnings("serial")
public class DefaultMessageSourceResolvable implements MessageSourceResolvable, Serializable {
	private final String[] codes;

	private final Object[] arguments;

	private final String defaultMessage;

	public DefaultMessageSourceResolvable(String code) {
		this(new String[] { code }, null, null);
	}

	public DefaultMessageSourceResolvable(String[] codes) {
		this(codes, null, null);
	}

	public DefaultMessageSourceResolvable(String[] codes, String defaultMessage) {
		this(codes, null, defaultMessage);
	}

	public DefaultMessageSourceResolvable(String[] codes, Object[] arguments) {
		this(codes, arguments, null);
	}

	public DefaultMessageSourceResolvable(String[] codes, Object[] arguments, String defaultMessage) {
		this.codes = codes;
		this.arguments = arguments;
		this.defaultMessage = defaultMessage;
	}

	public DefaultMessageSourceResolvable(MessageSourceResolvable resolvable) {
		this(resolvable.getCodes(), resolvable.getArguments(), resolvable.getDefaultMessage());
	}

	public String[] getCodes() {
		return this.codes;
	}

	public String getCode() {
		return (this.codes != null && this.codes.length > 0) ? this.codes[this.codes.length - 1] : null;
	}

	public Object[] getArguments() {
		return this.arguments;
	}

	public String getDefaultMessage() {
		return this.defaultMessage;
	}

	protected final String resolvableToString() {
		StringBuilder result = new StringBuilder();
		result.append("codes [").append(StringUtils.arrayToDelimitedString(this.codes, ","));
		result.append("]; arguments [" + StringUtils.arrayToDelimitedString(this.arguments, ","));
		result.append("]; default message [").append(this.defaultMessage).append(']');
		return result.toString();
	}

	public String toString() {
		return getClass().getName() + ":" + resolvableToString();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MessageSourceResolvable)) {
			return false;
		}
		MessageSourceResolvable otherResolvable = (MessageSourceResolvable) other;
		return ObjectUtils.nullSafeEquals(getCodes(), otherResolvable.getCodes())
				&& ObjectUtils.nullSafeEquals(getArguments(), otherResolvable.getArguments())
				&& ObjectUtils.nullSafeEquals(getDefaultMessage(), otherResolvable.getDefaultMessage());
	}

	@Override
	public int hashCode() {
		int hashCode = ObjectUtils.nullSafeHashCode(getCodes());
		hashCode = 29 * hashCode + ObjectUtils.nullSafeHashCode(getArguments());
		hashCode = 29 * hashCode + ObjectUtils.nullSafeHashCode(getDefaultMessage());
		return hashCode;
	}

}
