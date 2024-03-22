package com.mmdev.runner;

import com.mmdev.dao.FlightDao;
import com.mmdev.serivce.JdbcFlightDao;


import java.sql.SQLException;

import java.util.List;

public class Runner {
	public static void main(String[] args) throws SQLException {
		JdbcFlightDao jdbcFlightDao = new JdbcFlightDao();
		List<FlightDao> list = jdbcFlightDao.findAll();
		list.forEach(System.out::println);
	}
}
