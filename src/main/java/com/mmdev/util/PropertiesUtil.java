package com.mmdev.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

@UtilityClass
public  class PropertiesUtil {
	private static final Properties PROPERTIES = new Properties();

	static {
		loadProcessor();
	}
	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}

	private static void loadProcessor() {
		try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
			PROPERTIES.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
