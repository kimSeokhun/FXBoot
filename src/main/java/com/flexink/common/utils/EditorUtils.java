package com.flexink.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditorUtils {

	public static List<String> getImgSrc(String str) {
		Pattern nonValidPattern = Pattern
				.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

		List<String> result = new ArrayList<String>();
		Matcher matcher = nonValidPattern.matcher(str);
		while (matcher.find()) {
			result.add(matcher.group(1));
		}
		return result;
	}
	
	public static List<String> getImgIds(String str) {
		List<String> result = new ArrayList<String>();
		
		List<String> imgSrcs = getImgSrc(str);
		
		for(String s : imgSrcs) {
			if(s.contains("/files/preview/")) {
				result.add(s.substring(s.lastIndexOf("/") + 1));
			}
		}
		
		return result;
	}
}
