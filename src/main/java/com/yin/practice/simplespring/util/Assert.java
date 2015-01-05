package com.yin.practice.simplespring.util;

/**
 * Assertion utility class that assists in validating arguments. Useful for
 * identifying programmer errors early and clearly at runtime.
 * 断言实用类用来帮助验证参数。在运行期对于及时发现程序员的错误很有帮助
 * 
 * <p>
 * For example, if the contract of a public method states it does not allow
 * {@code null} arguments, Assert can be used to validate that contract. Doing
 * this clearly indicates a contract violation when it occurs and protects the
 * class's invariants.
 * 
 * <p>
 * 例如,在合同的一个公共方法中声明不允许存在为空的参数,那么,Assert就可以用来验证这个合同.
 * 这样做可以明显的指出合同在不符合条件时的违约性以及保护了类的不变量.
 * 
 * @author 尹逊志
 * @date 2014-12-17下午4:08:44
 */
public abstract class Assert {
    /**
     * 断言一个表达式是否为true,false抛出IllegalArgumentException
     * 
     * @author 尹逊志
     * @time 上午11:16:18
     * @param expression
     *            the exception message to use if the assertion fails
     * @param message
     *            IllegalArgumentException if expression is false
     */
    public static void isTrue(boolean expression, String message) {
	if (!expression) {
	    throw new IllegalArgumentException(message);
	}
    }

    /**
     * 
     * @author 尹逊志
     * @time 上午11:22:24
     * @param expression
     */
    public static void isTrue(boolean expression) {
	isTrue(expression, "[Assertion failed]-this expression must be true");
    }

    /**
     * 判断一个对象必须为空
     * 
     * @author 尹逊志
     * @time 上午11:25:25
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
	if (object != null) {
	    throw new IllegalArgumentException(message);
	}
    }

    /**
     * 判断一个对象必须为空
     * 
     * @author 尹逊志
     * @time 上午11:25:06
     * @param object
     */
    public static void isNull(Object object) {
	if (object != null) {
	    isNull(object,
		    "[Assertion failed] - the object argument must be null");
	}
    }

    public static void notNull(Object object, String message) {
	if (object == null) {
	    throw new IllegalArgumentException(message);
	}
    }

    public static void notNull(Object object) {
	notNull(object,
		"[Assertion failed] - this argument is required; it must not be null");
    }

    /**
     * 判断给定的字符串不能为空,也就是说不能为null并且也不能为空的字符串
     * 
     * @author 尹逊志
     * @time 下午2:41:09
     * @param text
     * @param message
     */
    public static void hasLegth(String text, String message) {
	if (!StringUtils.hasLegth(text)) {
	    throw new IllegalArgumentException(message);
	}
    }

    public static void hasLength(String text) {
	hasLegth(
		text,
		"[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    /**
     * 判断给定的字符串是否包含实际内容
     * 
     * @author 尹逊志
     * @time 下午2:49:25
     * @param text
     * @param message
     */
    public static void hasText(String text, String message) {
	if (!StringUtils.hasText(text)) {
	    throw new IllegalArgumentException(message);
	}
    }

    public static void hasText(String text) {
	hasText(text,
		"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    /**
     * 断言给定的文本内容不能包含特定的字符串
     * 
     * @author 尹逊志
     * @time 下午2:52:31
     * @param textToSearch
     * @param subString
     * @param message
     */
    public static void doesNotContain(String textToSearch, String substring,
	    String message) {
	if (StringUtils.hasLegth(textToSearch)
		&& StringUtils.hasLegth(substring)
		&& textToSearch.contains(substring)) {
	    throw new IllegalArgumentException(message);
	}
    }

    public static void doesNoeContain(String textToSearch, String substring) {
	doesNotContain(textToSearch, substring,
		"[Assertion failed] - this String argument must not contain the substring ["
			+ substring + "]");
    }
}
