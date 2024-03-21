package com.mmdev.runner;

import com.mmdev.util.ConnectionManagerUtil;

import java.sql.SQLException;

public class Runner {
	public static void main(String[] args) throws SQLException {
		try (var open = ConnectionManagerUtil.open()) {
			System.out.println(open.getTransactionIsolation());
		}
	}
}
