package com.mmdev.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
	Long id;
	String mail;
}
