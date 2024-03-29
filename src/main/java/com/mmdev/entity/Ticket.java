package com.mmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Ticket {
	private Long id;
	private String passengerNo;
	private String passengerName;
	private Long flightId;
	private String seatNo;
	private Long cost;
}
