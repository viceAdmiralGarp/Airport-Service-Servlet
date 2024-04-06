package com.mmdev.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; //TODO: unused import

@UtilityClass//TODO: Why are you using experimental feature?
public class ConnectionManagerUtil {

	private static final String PASSWORD_KEY = "db.password";
	private static final String USERNAME_KEY = "db.username";
	private static final String URL = "db.url";

	static {
		loadDriver();
	}

	private static void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SneakyThrows//TODO: using SneakyThrows is not recommended
	public static Connection open() {
		return DriverManager.getConnection(PropertiesUtil.get(URL),
				PropertiesUtil.get(USERNAME_KEY),
				PropertiesUtil.get(PASSWORD_KEY));

	}
}
