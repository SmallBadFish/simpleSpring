package com.yin.practice.simplespring.util;

import org.junit.Test;

public class StringUtilsTest {
	@Test
	public void cleanPath() {
		String pathToUse = StringUtils
				.cleanPath("C:\\Users\\Administrator\\Desktop");
		System.out.println(pathToUse);
	}
	@Test
	public void deleteAny(){
		String inString = "1,2,3,4,5,,,7";
		String str = StringUtils.deleteAny(inString, ",,");
		System.out.println(str);
	}
	@Test
	public void delimitedListToStringArray(){
//		String inString = "1,2,3,4,5,,,,7";
		String inString = "Users/Administrator/Desktop";
		String[] strArry = StringUtils.delimitedListToStringArray(inString, "/");
//		String inString = "1,2,3,4,5,,,,7";
//		String inString = "1,2,3,4,5,,,,7";
//		String[] strArry = StringUtils.delimitedListToStringArray(inString, ",");
		for(int i=0;i<strArry.length;i++){
			System.out.println(strArry[i]);
		}
	}
}
