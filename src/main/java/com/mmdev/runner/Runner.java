package com.mmdev.runner;


import com.mmdev.dao.UserDao;
import com.mmdev.entity.Ticket;
import com.mmdev.dao.TicketDao;
import com.mmdev.entity.User;

import java.sql.Date;
import java.sql.SQLException;


public class Runner {
	public static void main(String[] args) throws SQLException {
		User user = User.builder()
				.name("Vasya")
				.email("Vasya@gmail.com")
				.password("123")
				.birthday(new Date(2000))
				.role("User")
				.gender("Mail")
				.build();
		UserDao userDao = UserDao.getInstance();
		userDao.findAll().forEach(System.out::println);
	}
}
