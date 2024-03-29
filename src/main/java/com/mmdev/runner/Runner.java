package com.mmdev.runner;

import com.mmdev.dao.FlightDao;
import com.mmdev.dao.UserDao;
import com.mmdev.entity.Flight;
import com.mmdev.entity.Ticket;
import com.mmdev.dao.TicketDao;
import com.mmdev.entity.User;


import java.sql.Date;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.List;

public class Runner {
	public static void main(String[] args) throws SQLException {
		TicketDao ticketDao = TicketDao.getInstance();
		var byId = ticketDao.findById(1L);
		System.out.println(byId.toString());
	}
}
