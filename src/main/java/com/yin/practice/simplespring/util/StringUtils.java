package com.yin.practice.simplespring.util;

/**
 * 字符串工具类
 * 
 * @author 尹逊志
 * @time 上午11:32:12
 */
public abstract class StringUtils {
    private static final String FOLDER_SEPARATOR = "/";// 文件夹分割符
    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";// windows下文件夹分隔符
    private static final String TOP_PATH = "..";
    private static final String CURRENT_PATH = ".";
    private static final char EXTENSION_SEPARATOR = '.';

    /**
     * 检查给定的字符串是否为空
     * <P>
     * 这个方法接受一个对象作为参数,该对象与null和空字符串作比较.
     * 
     * @author 尹逊志
     * @time 上午11:52:43
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
	return (str == null || "".equals(str));
    }

    /**
     * 检查给定的字符串是否为空或者为空字符串
     * 
     * <pre class="code">
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     * 
     * @author 尹逊志
     * @time 下午12:18:44
     * @param str
     * @return
     */
    public static boolean hasLegth(CharSequence str) {
	return (str != null && str.length() > 0);
    }

    public static boolean hasLength(String str) {
	return hasLegth((CharSequence) str);
    }

    /**
     * 检查给定的字符串是否含有文本内容
     * 
     * @author 尹逊志
     * @time 下午2:15:37
     * @param str
     * @return
     */
    public static boolean hasText(CharSequence str) {
	if (!hasLegth(str)) {
	    return false;
	}
	int strLen = str.length();
	for (int i = 0; i < strLen; i++) {
	    if (!Character.isWhitespace(str.charAt(i))) {
		return true;
	    }
	}
	return false;
    }

    public static boolean hasText(String str) {
	return hasText((CharSequence) str);
    }

    /**
     * 检查给定的字符串是否包含空格
     * 
     * @author 尹逊志
     * @time 下午2:19:50
     * @param str
     * @return
     */
    public static boolean containsWhitespace(CharSequence str) {
	if (!hasLegth(str)) {
	    return false;
	}
	int strLen = str.length();
	for (int i = 0; i < strLen; i++) {
	    if (Character.isWhitespace(str.charAt(i))) {
		return true;
	    }
	}
	return false;
    }

    public static boolean containWhitespace(String str) {
	return containsWhitespace((CharSequence) str);
    }

    /**
     * 去掉字符串首尾的空格
     * 
     * @author 尹逊志
     * @time 下午2:32:49
     * @param str
     * @return
     */
    public static String trimWhitespace(String str) {
	if (!hasLegth(str)) {
	    return str;
	}
	StringBuilder sb = new StringBuilder(str);
	while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
	    sb.deleteCharAt(0);
	}
	while (sb.length() > 0
		&& Character.isWhitespace(sb.charAt(sb.length() - 1))) {
	    sb.deleteCharAt(sb.length() - 1);
	}
	return sb.toString();
    }

    /**
     * 去掉给定字符串的所有空格
     * 
     * @author 尹逊志
     * @time 下午2:36:32
     * @param str
     * @return
     */
    public static String trimAllWhitespace(String str) {
	if (!hasLength(str)) {
	    return str;
	}
	StringBuilder sb = new StringBuilder(str);
	int index = 0;
	while (sb.length() > index) {
	    if (Character.isWhitespace(sb.charAt(index))) {
		sb.deleteCharAt(index);
	    } else {
		index++;
	    }
	}
	return sb.toString();
    }

    public static String cleanPath(String path) {
	if (path == null) {
	    return null;
	}
	String pathToUse = replace(path, WINDOWS_FOLDER_SEPARATOR,
		FOLDER_SEPARATOR);
	// Strip prefix from path to analyze, to not treat it as part of the
	// first path element. This is necessary to correctly parse paths like
	// "file:core/../core/io/Resource.class", where the ".." should just
	// strip the first "core" directory while keeping the "file:" prefix.
	int prefixIndex = pathToUse.indexOf(":");
	String prefix = "";
	if (prefixIndex != -1) {
	    prefix = pathToUse.substring(0, prefixIndex + 1);
	    if (prefix.contains("/")) {
		prefix = "";
	    } else {
		pathToUse = pathToUse.substring(prefixIndex + 1);
	    }
	}
	if(pathToUse.startsWith(FOLDER_SEPARATOR)){
	    prefix = prefix +FOLDER_SEPARATOR;
	    pathToUse = pathToUse.substring(1);
	}
	return null;
    }

    /**
     * 使用一个新的字符串替换原字符串中指定的字字符串
     * 
     * @author 尹逊志
     * @time 下午4:30:13
     * @param path
     * @param olderPattern
     * @param newPattern
     * @return
     */
    private static String replace(String inString, String olderPattern,
	    String newPattern) {
	if (!hasLegth(inString) || !hasLegth(olderPattern)
		|| newPattern == null) {
	    return inString;
	}
	StringBuilder sb = new StringBuilder();
	int pos = 0;// 要被替换的字符串的初始化位置
	int index = inString.indexOf(olderPattern);// 被替换字符串在原字符串中出现的位置,如果不存在,返回-1
	int patLen = olderPattern.length();// 被替换字符串的位置
	while (index >= 0) {
	    sb.append(inString.substring(pos, index));// 构建初始化位置到被替换字符串出现的位置的Stringbuild
	    sb.append(newPattern);// 在被替换字符串出现的位置追加替换字符串
	    pos = index + patLen;// 初始化索引位置重新赋值
	    index = inString.indexOf(olderPattern, pos);// 从新的索引位置查找被替换字符串的位置
	}
	sb.append(inString.substring(pos));
	return sb.toString();
    }
}
