package com.mmdev.runner;

import com.mmdev.entity.Ticket;
import com.mmdev.dao.TicketDao;


import java.sql.SQLException;

import java.util.List;

public class Runner {
	public static void main(String[] args) throws SQLException {
		TicketDao ticketDao = TicketDao.getInstance();
		List<Ticket> list = ticketDao.findAll();
		list.forEach(System.out::println);
	}
}
