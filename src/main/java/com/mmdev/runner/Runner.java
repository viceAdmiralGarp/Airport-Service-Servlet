package com.mmdev.runner;


import com.mmdev.entity.Ticket;
import com.mmdev.dao.TicketDao;

import java.sql.SQLException;


public class Runner {
	public static void main(String[] args) throws SQLException {
		Ticket ticket = Ticket.builder()
				.id(57L)
				.passengerNo("123951")
				.passengerName("Vasya")
				.flightId(9L)
				.seatNo("D3")
				.cost(217L)
				.build();
		TicketDao ticketDao = TicketDao.getInstance();
		ticketDao.remove(ticket);
	}
}
