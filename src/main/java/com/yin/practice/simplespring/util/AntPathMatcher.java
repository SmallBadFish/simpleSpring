package com.yin.practice.simplespring.util;

import java.util.Comparator;
import java.util.Map;
import java.util.regex.Pattern;

public class AntPathMatcher implements PathMathcer {
	// ��̬����
	// Ĭ��·���ָ��
	public static final String DEFAULT_PATH_SEPARATOR = "/";
	public static final int CACHE_TURNOFF_THRESHOLD = 65536;
	private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{[^/]+?\\}");

	// ��Ա����
	private String pathSeparator = DEFAULT_PATH_SEPARATOR;
	private boolean trimTokens = true;
	private volatile Boolean cachePatterns;
	private PathSeparatorPatternCache pathSeparatorPatternCache = new PathSeparatorPatternCache(DEFAULT_PATH_SEPARATOR);

	/**
	 * �չ��캯�� ����һ��ʵ��
	 */
	public AntPathMatcher() {
	}

	public AntPathMatcher(String pathSeparator) {
		Assert.notNull(pathSeparator, "'pathSeparator' is required");
		this.pathSeparator = pathSeparator;
		this.pathSeparatorPatternCache = new PathSeparatorPatternCache(pathSeparator);
	}

	/**
	 * ����Apache Ant��Լ��,ant��������ͨ���'?','*','**'.<br>
	 * ������·���а�����*�ͣ��Ϳ�����Ϊһ��·��ƥ��ģʽ��ʹ��
	 */
	public boolean isPattern(String path) {
		return (path.indexOf('*') != -1 || path.indexOf('?') != -1);
	}

	/**
	 * ���ݸ�����·���͹������ƥ��
	 */
	public boolean match(String pattern, String path) {
		return doMatch(pattern, path, true, null);
	}

	public boolean matchStart(String pattern, String path) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ���й�����֤
	 * 
	 * @param pattern
	 *            ƥ��ģʽ
	 * @param path
	 *            ·��
	 * @param fullMatch
	 * @param uriTemplateVariables
	 * @return
	 */
	protected boolean doMatch(String pattern, String path, boolean fullMatch,
			Map<String, String> uriTemplateVariables) {
		// �ж�·����ƥ������Ƿ���·���ָ����ʼ
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
	 * �������õ�·���ָ����ӳ�����ļ򵥻������
	 */
	private static class PathSeparatorPatternCache {
		private final String endsOnWildCard;// ��ͨ�������
		private final String endsOnDoubleWildCard;// ������ͨ�������

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
