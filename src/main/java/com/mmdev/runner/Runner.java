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
		FlightDao flightDao = FlightDao.getInstance();
		flightDao.create(Flight.builder()
				.flightNo("5")
				.departureDate(new Timestamp(2000))
				.departureAirportCode("MNK")
				.arrivalDate(new Timestamp(2000))
				.arrivalAirportCode("MNK")
				.aircraftId(4)
				.status("wait")
				.build());

		flightDao.findAll().forEach(System.out::println);
	}

}
