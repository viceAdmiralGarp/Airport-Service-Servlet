package com.mmdev.mapper;

import com.mmdev.dto.UserDto;
import com.mmdev.entity.Gender;
import com.mmdev.entity.Role;
import com.mmdev.entity.User;

import java.sql.Date;

public class UserMapper implements Mapper<UserDto, User> {//TODO: map(UserDto userDto) usually placed inside the DTO class with name "toUser()"

	private static final UserMapper INSTANCE = new UserMapper();

	private UserMapper() {
	}

	@Override
	public User map(UserDto userDto) {
		return User.builder()
				.name(userDto.getName())
				.email(userDto.getEmail())
				.password(userDto.getPassword())
				.birthday(Date.valueOf(userDto.getBirthday()))
				.role(Role.valueOf(userDto.getRole().toUpperCase()))
				.gender(Gender.valueOf(userDto.getGender().toUpperCase()))
				.build();
	}

	public static UserMapper getInstance() {
		return INSTANCE;
	}//TODO: all getInstance() methods should be on the top of the class

}
