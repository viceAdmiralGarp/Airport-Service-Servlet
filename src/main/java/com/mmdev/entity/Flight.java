package com.mmdev.entity;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Flight {
	private Long id;
	private String flightNo;
	private Timestamp departureDate;
	private String departureAirportCode;
	private Timestamp arrivalDate;
	private String arrivalAirportCode;
	private Integer aircraftId;
	private String Status;
}
