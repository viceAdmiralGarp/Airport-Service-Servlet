package com.mmdev.entity;

import lombok.Data;

@Data
public class Ticket {
	private Long id;
	private String passengerNo;
	private String passengerName;
	private Long flightId;
	private String seatNo;
	private Long Cost;
}
