package com.mmdev.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
	private static final String PATH = "/WEB-INF/jsp/";

	public static String Path(String path) {
		return PATH + "%s.jsp".formatted(path);
	}
}
