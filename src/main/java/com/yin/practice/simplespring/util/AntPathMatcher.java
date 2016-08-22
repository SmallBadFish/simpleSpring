package com.yin.practice.simplespring.util;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

public class AntPathMatcher implements PathMathcer {
	// 静态常量
	// 默认路径分割符
	public static final String DEFAULT_PATH_SEPARATOR = "/";
	public static final int CACHE_TURNOFF_THRESHOLD = 65536;
	private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{[^/]+?\\}");

	// 成员变量
	// 默认的路径分割符为"/"
	private String pathSeparator = DEFAULT_PATH_SEPARATOR;
	private boolean trimTokens = true;
	// 是否对pattern(正则)进行缓存
	private volatile Boolean cachePatterns;
	// 如果cachePatterns为空或者cachePatterns为true时,使用该集合对pattern进行缓存
	private final Map<String, String[]> tokenizedPatternCache = new ConcurrentHashMap<String, String[]>(256);
	private final Map<String, AntPathStringMatcher> stringMatcherCache = new ConcurrentHashMap<String, AntPathStringMatcher>(
			256);
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
		return doMatch(pattern, path, false, null);
	}

	/**
	 * 进行规则验证
	 * 
	 * @param pattern
	 *            匹配模式
	 * @param path
	 *            路径
	 * @param fullMatch
	 *            是否完整匹配
	 * @param uriTemplateVariables
	 * @return
	 */
	protected boolean doMatch(String pattern, String path, boolean fullMatch,
			Map<String, String> uriTemplateVariables) {
		// 判定路径和匹配规则是否都是已路径分割符开始
		if (path.startsWith(this.pathSeparator) != path.startsWith(this.pathSeparator)) {
			return false;
		}
		// pattern分割后的String数组
		String[] pattDirs = tokenizePattern(pattern);
		// path按照'/'分割符分割后的String数组
		String[] pathDirs = tokenizePath(path);

		// pattern 开始索引
		int pattIdxStart = 0;
		// pattern 结束索引
		int pattIdxEnd = pattDirs.length - 1;
		// path 开始索引
		int pathIdxStart = 0;
		// path 结束索引
		int pathIdxEnd = pathDirs.length - 1;

		// 从 ** 通配符开始自上而下匹配
		while (pattIdxStart <= pattIdxEnd && pathIdxStart <= pathIdxEnd) {
			String pattDir = pattDirs[pathIdxStart];
			// ** 通配符匹配任何路径
			if ("**".equals(pattDir)) {
				break;
			}
			if (matchStrings(pattDir, pathDirs[pathIdxStart], uriTemplateVariables)) {
				return false;
			}
			pattIdxStart++;
			pathIdxStart++;
		}

		if (pathIdxStart > pathIdxEnd) {
			if (pattIdxStart > pattIdxEnd) {
				return (pattern.endsWith(this.pathSeparator) == path.endsWith(this.pathSeparator));
			}
			if (!fullMatch) {
				return true;
			}
			if (pattIdxStart == pattIdxEnd && pattDirs[pattIdxStart].equals("*") && path.endsWith(this.pathSeparator)) {
				return true;
			}
			for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
				if (!pattDirs[i].equals("**")) {
					return false;
				}
			}
			return true;
		} else if (pattIdxStart > pattIdxEnd) {
			return true;
		}

		while (pattIdxStart <= pattIdxEnd && pathIdxStart <= pathIdxEnd) {
			String pattDir = pattDirs[pattIdxEnd];
			if (pattDir.equals("**")) {
				break;
			}
			if (!matchStrings(pattDir, pathDirs[pathIdxEnd], uriTemplateVariables)) {
				return false;
			}
			pattIdxEnd--;
			pathIdxEnd--;
		}

		if (pathIdxStart > pathIdxEnd) {
			for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
				if (!pattDirs[i].equals("**")) {
					return false;
				}
			}
			return true;
		}

		while (pattIdxStart != pattIdxEnd && pathIdxStart <= pathIdxEnd) {
			int patIdxTmp = -1;
			for (int i = pattIdxStart + 1; i <= pattIdxEnd; i++) {
				if (pattDirs[i].equals("**")) {
					patIdxTmp = i;
					break;
				}
			}
			if (patIdxTmp == pattIdxStart + 1) {
				pattIdxStart++;
				continue;
			}
			int patLength = (patIdxTmp - pattIdxStart - 1);
			int strLength = (pathIdxEnd - pathIdxStart + 1);
			int foundIdx = -1;

			strLoop: for (int i = 0; i <= strLength - patLength; i++) {
				for (int j = 0; j < patLength; j++) {
					String subPat = pattDirs[pattIdxStart + j + 1];
					String subStr = pathDirs[pathIdxStart + i + j];
					if (!matchStrings(subPat, subStr, uriTemplateVariables)) {
						continue strLoop;
					}
				}
				foundIdx = pathIdxStart + i;
				break;
			}
			if (foundIdx == -1) {
				return false;
			}
			pattIdxStart = patIdxTmp;
			pathIdxStart = foundIdx + patLength;
		}

		for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
			if (!pattDirs[i].equals("**")) {
				return false;
			}
		}
		return true;
	}

	private boolean matchStrings(String pattern, String str, Map<String, String> uriTemplateVariables) {
		return getStringMatcher(pattern).matchStrings(str, uriTemplateVariables);
	}

	private AntPathStringMatcher getStringMatcher(String pattern) {
		AntPathStringMatcher matcher = null;
		Boolean cachePatterns = this.cachePatterns;
		if (cachePatterns == null || cachePatterns.booleanValue()) {
			matcher = this.stringMatcherCache.get(pattern);
		}
		if (matcher == null) {
			matcher = new AntPathStringMatcher(pattern);
			if (cachePatterns == null && this.stringMatcherCache.size() >= CACHE_TURNOFF_THRESHOLD) {
				deactivatePatternCache();
				return matcher;
			}
		}
		return matcher;
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
	 * 对路径模式(正则表达式)进行标记(分词)
	 * 
	 * @param pattern
	 * @return
	 */
	protected String[] tokenizePattern(String pattern) {
		String[] tokenized = null;
		Boolean cachePatterns = this.cachePatterns;
		if (cachePatterns == null || cachePatterns.booleanValue()) {
			tokenized = this.tokenizedPatternCache.get(pattern);
		}
		if (tokenized == null) {
			tokenized = tokenizePath(pattern);
			if (cachePatterns == null && this.tokenizedPatternCache.size() >= CACHE_TURNOFF_THRESHOLD) {
				deactivatePatternCache();
				return tokenized;
			}
			if (cachePatterns == null || cachePatterns.booleanValue()) {
				this.tokenizedPatternCache.put(pattern, tokenized);
			}
		}
		return tokenized;
	}

	/**
	 * 使集合缓存无效
	 */
	private void deactivatePatternCache() {
		this.cachePatterns = false;
		this.tokenizedPatternCache.clear();
		this.stringMatcherCache.clear();
	}

	protected String[] tokenizePath(String path) {
		return StringUtils.tokenizeToStringArray(path, this.pathSeparator, this.trimTokens, true);
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

	/**
	 * 内部静态类检查一个String(字符串)是否和一个Pattern(模式或者匹配规则)相匹配<br>
	 * pattern会包含特定的字符:'*'表示0个或多个字符,'?'表示一个而且仅有一个字符,'{}'表示一个URI模板pattern
	 */
	protected static class AntPathStringMatcher {
		// 正则表达式: \?|\*|\{((?:\{[^/]+?\}|[^/{}]|\\[{}])+?)\}
		// 正则表达式解析:\? \* \{((?:\{[^/]+?\}|[^/{}]|\\[{}])+?)\}
		// 前面两组易懂 可以匹配字符'?'和'*'
		// 最后一组拆分: \{ ((?:\{[^/]+?\}|[^/{}]|\\[{}])+?) \} 可以看出必须字符'{'开头字符'}'结尾
		// 里面内容拆分: (?:)模式匹配 \{[^/]+?\} [^/{}] \\[{}] +? 匹配一次或多次
		// 三组分别匹配的内容为 : 除/之外的任何字符匹配 ,除'/''{''}'之外的任何字符匹配,与'\{'或者'\}'匹配
		private static final Pattern GLOB_PATTERN = Pattern
				.compile("\\?|\\*|\\{((?:\\{[^/]+?\\}|[^/{}]|\\\\[{}])+?)\\}");
		private static final String DEFAULT_VATIABLE_PATTERN = "(.*)";
		private final Pattern pattern;
		private final List<String> variableNames = new LinkedList<String>();

		public AntPathStringMatcher(String pattern) {
			StringBuilder patternBuilder = new StringBuilder();
			Matcher m = GLOB_PATTERN.matcher(pattern);
			int end = 0;
			while (m.find()) {
				patternBuilder.append(quote(pattern, end, m.start()));
				String match = m.group();
				if ("?".equals(match)) {
					patternBuilder.append('.');
				} else if ("*".equals(match)) {
					patternBuilder.append(".*");
				} else if (match.startsWith("{") && match.endsWith("}")) {
					int colonIdx = match.indexOf(":");
					if (colonIdx == -1) {
						patternBuilder.append(DEFAULT_VATIABLE_PATTERN);
						this.variableNames.add(m.group(1));
					} else {
						String variablePattern = match.substring(colonIdx + 1, match.length() - 1);
						patternBuilder.append('(');
						patternBuilder.append(variablePattern);
						patternBuilder.append(')');
						String variableName = match.substring(1, colonIdx);
						this.variableNames.add(variableName);
					}
				}
				end = m.end();
			}
			patternBuilder.append(quote(pattern, end, pattern.length()));
			this.pattern = Pattern.compile(patternBuilder.toString());
		}

		private String quote(String s, int start, int end) {
			if (start == end) {
				return "";
			}
			return Pattern.quote(s.substring(start, end));
		}

		public boolean matchStrings(String str, Map<String, String> uriTemplateVariables) {
			Matcher matcher = this.pattern.matcher(str);
			if (matcher.matches()) {
				if (uriTemplateVariables != null) {
					Assert.isTrue(this.variableNames.size() == matcher.groupCount(),
							"The number of capturing groups in the pattern segment " + this.pattern
									+ " does not match the number of URI template variables it defines, which can occur if "
									+ " capturing groups are used in a URI template regex. Use non-capturing groups instead.");
					for (int i = 1; i < matcher.groupCount(); i++) {
						String name = this.variableNames.get(i - 1);
						String value = matcher.group();
						uriTemplateVariables.put(name, value);
					}
				}
				return true;
			} else {
				return false;
			}
		}
	}

}
