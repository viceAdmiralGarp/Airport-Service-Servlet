package com.mmdev.serivce;

import com.mmdev.dao.FlightDao;
import com.mmdev.dto.FlightDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FlightService {

	private final FlightService INSTANCE = new FlightService();
	private final FlightDao flightDao = FlightDao.getInstance();

	private FlightService() {
	}

	public List<FlightDto> findAll() {
		return flightDao.findAll().stream()
				.map(flight -> new FlightDto(
						flight.getId(),
						"""
							% - % - %
						""".formatted(
								flight.getDepartureAirportCode(),
								flight.getArrivalAirportCode(),
								flight.getStatus())))
						.collect(toList());
	}

	public FlightService getInstance() {
		return INSTANCE;
	}
}
