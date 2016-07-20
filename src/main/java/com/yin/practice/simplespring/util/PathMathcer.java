package com.yin.practice.simplespring.util;

import java.util.Comparator;
import java.util.Map;

/**
 * 该接口定义了简单策略用来验证匹配字符串格式的路径
 *
 */
public interface PathMathcer {

	// 路径参数是否可以作为路径模式用来匹配其他路径
	boolean isPattern(String path);

	boolean match(String pattern, String path);

	boolean matchStart(String pattern, String path);

	String extractPathWithinPattern(String pattern, String path);

	Map<String, String> extractUriTemplaterVariables(String pattern, String path);

	Comparator<String> getPatternComparator(String path);

	String combine(String pattern1, String pattern2);
}
