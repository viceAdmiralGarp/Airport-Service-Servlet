package com.mmdev.runner;



import com.mmdev.dao.UserDao;
import com.mmdev.entity.Gender;
import com.mmdev.entity.Role;
import com.mmdev.entity.User;

import java.sql.Date;
import java.sql.SQLException;


public class Runner {
	public static void main(String[] args) throws SQLException {
		User user = User.builder()
				.name("Kolya")
				.email("Kolya@gmail.com")
				.password("123")
				.birthday(new Date(2000))
				.role(Role.USER)
				.gender(Gender.MALE)
				.build();
		UserDao userDao = UserDao.getInstance();
		userDao.create(user);

	}
}
