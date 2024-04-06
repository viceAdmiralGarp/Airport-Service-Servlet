package com.mmdev.dto;

import lombok.*;

@Value//TODO: why do you need @Value here?
public class FlightDto {//TODO: use records instead of classes here
	 Long id;
	 String description;
}
