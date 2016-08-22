package com.yin.practice.simplespring.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


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
		return hasLegth(str);
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
		return containsWhitespace(str);
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

	/**
	 * 规范化给定的路径,禁止使用像"path/.."和中间点分隔的路径
	 * 
	 * @author yinxunzhi
	 * @time 2015年2月12日上午11:56:12
	 * @param path
	 * @return
	 */
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
		if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
			prefix = prefix + FOLDER_SEPARATOR;
			pathToUse = pathToUse.substring(1);
		}

		String[] pathArray = delimitedListToStringArray(pathToUse,
				FOLDER_SEPARATOR);
		List<String> pathElements = new LinkedList<String>();
		int tops = 0;
		for (int i = pathArray.length - 1; i >= 0; i--) {
			String element = pathArray[i];
			if (CURRENT_PATH.equals(element)) {

			} else if (TOP_PATH.equals(element)) {
				tops++;
			} else {
				if (tops > 0) {
					tops--;
				}else{
					pathElements.add(0, element);
				}
			}
		}
		for(int i=0;i<tops;i++){
			pathElements.add(0, TOP_PATH);
		}
		return prefix + collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
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

	/**
	 * 
	 * @author yinxunzhi
	 * @time 2015年3月11日下午5:56:18
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String[] delimitedListToStringArray(String str,
			String delimiter) {
		return delimitedListToStringArray(str, delimiter, null);
	}

	public static String[] delimitedListToStringArray(String str,
			String delimiter, String charsToDelete) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] { str };
		}
		List<String> result = new ArrayList<String>();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++) {
				result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
			}
		} else {
			int pos = 0;
			int delpos;
			while ((delpos = str.indexOf(delimiter, pos)) != -1) {
				result.add(deleteAny(str.substring(pos, delpos), delimiter));
				pos = delpos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				result.add(deleteAny(str.substring(pos), charsToDelete));
			}
		}
		return toStringArray(result);
	}

	/**
	 * Delete any character in a given String.
	 * 
	 * @author yinxunzhi
	 * @time 2015年3月11日下午6:03:50
	 * @param inString
	 *            the original String.
	 * @param charsToDelete
	 *            a set of characters to delete.
	 * @return
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (!hasLegth(inString) || !hasLegth(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @author yinxunzhi
	 * @time 2015年3月11日下午6:36:22
	 * @param collection
	 * @return
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}
	
	public static String collectionToDelimitedString(Collection<?> coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}
	
	public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while(it.hasNext()){
			sb.append(prefix).append(it.next()).append(suffix);
			if(it.hasNext()){
				sb.append(delim);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将分割字符串后的token转换为一个String数组
	 * @param str 要分割的字符串
	 * @param delimiters 分割标识符
	 * @param trimTokens 是否去掉空格
	 * @param ignoreEmptyTokens 是否忽略空的Token
	 * @return
	 */
	public static String[] tokenizeToStringArray(String str,
			String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
		if(str==null){
			return null;
		}
		StringTokenizer st = new StringTokenizer(str,delimiters);
		List<String> tokens = new ArrayList<String>();
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			if(trimTokens){
				token.trim();
			}
			if(!ignoreEmptyTokens || token.length() > 0){
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}
}
