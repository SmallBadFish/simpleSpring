package com.yin.practice.simplespring.util;

import java.util.Comparator;
import java.util.Map;
import java.util.regex.Pattern;

public class AntPathMatcher implements PathMathcer {
	// 静态常量
	// 默认路径分割符
	public static final String DEFAULT_PATH_SEPARATOR = "/";
	public static final int CACHE_TURNOFF_THRESHOLD = 65536;
	private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{[^/]+?\\}");

	// 成员变量
	private String pathSeparator = DEFAULT_PATH_SEPARATOR;
	private boolean trimTokens = true;
	private volatile Boolean cachePatterns;
	private PathSeparatorPatternCache pathSeparatorPatternCache = new PathSeparatorPatternCache(DEFAULT_PATH_SEPARATOR);

	/**
	 * 空构造函数 创建一个实例
	 */
	public AntPathMatcher() {
	}

	public AntPathMatcher(String pathSeparator) {
		Assert.notNull(pathSeparator, "'pathSeparator' is required");
		this.pathSeparator = pathSeparator;
		this.pathSeparatorPatternCache = new PathSeparatorPatternCache(pathSeparator);
	}

	/**
	 * 根据Apache Ant的约定,ant含有三种通配符'?','*','**'.<br>
	 * 因此如果路径中包含有*和？就可以做为一种路径匹配模式来使用
	 */
	public boolean isPattern(String path) {
		return (path.indexOf('*') != -1 || path.indexOf('?') != -1);
	}

	/**
	 * 根据给定的路径和规则进行匹配
	 */
	public boolean match(String pattern, String path) {
		return doMatch(pattern, path, true, null);
	}

	public boolean matchStart(String pattern, String path) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 进行规则验证
	 * 
	 * @param pattern
	 *            匹配模式
	 * @param path
	 *            路径
	 * @param fullMatch
	 * @param uriTemplateVariables
	 * @return
	 */
	protected boolean doMatch(String pattern, String path, boolean fullMatch,
			Map<String, String> uriTemplateVariables) {
		// 判定路径和匹配规则是否已路径分割符开始
		if (path.startsWith(this.pathSeparator) != path.startsWith(this.pathSeparator)) {
			return false;
		}
		
		return false;
	}

	public String extractPathWithinPattern(String pattern, String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> extractUriTemplaterVariables(String pattern, String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comparator<String> getPatternComparator(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public String combine(String pattern1, String pattern2) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * A simple cache for patterns that depend on the configured path separator.
	 * 根据配置的路径分割符对映射规则的简单缓存对象
	 */
	private static class PathSeparatorPatternCache {
		private final String endsOnWildCard;// 以通配符结束
		private final String endsOnDoubleWildCard;// 以两个通配符结束

		private PathSeparatorPatternCache(String pathSeparator) {
			this.endsOnWildCard = pathSeparator + "*";
			this.endsOnDoubleWildCard = pathSeparator + "**";
		}

		public String getEndsOnWildCard() {
			return endsOnWildCard;
		}

		public String getEndsOnDoubleWildCard() {
			return endsOnDoubleWildCard;
		}
	}

}
