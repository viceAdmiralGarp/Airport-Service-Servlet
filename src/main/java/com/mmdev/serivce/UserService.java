package com.mmdev.serivce;

import com.mmdev.dao.UserDao;
import com.mmdev.dto.UserDto;
import com.mmdev.entity.Gender;
import com.mmdev.entity.Role;
import com.mmdev.entity.User;

import java.sql.Date;


public class UserService {

	private final static UserService INSTANCE = new UserService();
	public static UserDao userDao = UserDao.getInstance();

	private UserService() {
	}

	public void create(UserDto userDto){
		User user = User.builder()
				.name(userDto.getName())
				.email(userDto.getEmail())
				.password(userDto.getPassword())
				.birthday(Date.valueOf(userDto.getBirthday()))
				.role(Role.valueOf(userDto.getRole().toUpperCase()))
				.gender(Gender.valueOf(userDto.getGender().toUpperCase()))
				.build();
		userDao.create(user);
	}

	public static UserService getInstance() {
		return INSTANCE;
	}
}
