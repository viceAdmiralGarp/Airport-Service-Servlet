package com.mmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data//TODO @Data is not recommended for entities
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
	private Date birthday;
	private Role role;
	private Gender gender;
}
