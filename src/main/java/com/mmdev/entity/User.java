package com.mmdev.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
	private Date birthday;
	private String role;
	private String gender;
}
