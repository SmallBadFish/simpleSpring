package com.yin.practice.simplespring.web.util.matcher;

import javax.servlet.http.HttpServletRequest;

/**
 * ant���͵�url·��ӳ����� 
 *
 */
public class AntPathRequestMatcher implements RequestMathcer{
	@Override
	public boolean matches(HttpServletRequest request) {
		return false;
	}

//	private static final Log looger = LogFactory.getLog(AntPathRequestMatcher.class);
//	private static final String MATCH_ALL = "/**";
//
//	private final Matcher matcher;
//
//	public boolean matches(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//	private interface Matcher{
//		boolean matches(String path);
//
//		Map<String,String> extractUriTemplateVariables(String path);
//	}
//
//
//	private static class SpringAntMatcher implements Matcher{
//		private static final AntPathMatcher antMatcher = createMatcher();
//
//		public boolean matches(String path) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		public Map<String, String> extractUriTemplateVariables(String path) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//	}
//
//	private static class SubpathMatcher implements Matcher{
//
//		public boolean matches(String path) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		public Map<String, String> extractUriTemplateVariables(String path) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//	}
}
