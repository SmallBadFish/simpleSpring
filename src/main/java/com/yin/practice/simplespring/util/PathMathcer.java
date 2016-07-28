package com.yin.practice.simplespring.util;

import java.util.Comparator;
import java.util.Map;

/**
 * �ýӿڶ����˼򵥲���������֤ƥ���ַ�����ʽ��·��
 *
 */
public interface PathMathcer {

	// ·�������Ƿ������Ϊ·��ģʽ����ƥ������·��
	boolean isPattern(String path);

	// ����pattern(ƥ��ģʽ)ȥƥ��·��,����ƥ��
	boolean match(String pattern, String path);

	// ����pattern(ƥ��ģʽ)ȥƥ��·��,������ƥ��
	boolean matchStart(String pattern, String path);

	String extractPathWithinPattern(String pattern, String path);

	Map<String, String> extractUriTemplaterVariables(String pattern, String path);

	Comparator<String> getPatternComparator(String path);

	String combine(String pattern1, String pattern2);
}
