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
		Flight flight = Flight.builder()
				.id(10L)
				.flightNo("6")
				.departureDate(new Timestamp(60L))
				.departureAirportCode("MSK")
				.arrivalDate(new Timestamp(80L))
				.arrivalAirportCode("LDN")
				.aircraftId(1)
				.status("ARRIVED")
				.build();
		FlightDao flightDao = FlightDao.getInstance();
		flightDao.update(flight);


	}

}
