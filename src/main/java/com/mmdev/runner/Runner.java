package com.mmdev.runner;

import com.mmdev.dao.TicketDao;
import com.mmdev.serivce.JdbcTicketDao;


import java.sql.SQLException;

import java.util.List;

public class Runner {
	public static void main(String[] args) throws SQLException {
		JdbcTicketDao jdbcTicketDao = new JdbcTicketDao();
		List<TicketDao> list = jdbcTicketDao.findAll();
		list.forEach(System.out::println);
	}
}
