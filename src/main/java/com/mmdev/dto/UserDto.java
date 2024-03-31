package com.mmdev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;


import java.sql.Date;

@Value
@Builder
@AllArgsConstructor
public class UserDto {
	String name;
	String email;
	String password;
	String birthday;
	String role;
	String gender;
}
