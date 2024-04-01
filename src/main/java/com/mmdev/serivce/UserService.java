package com.mmdev.serivce;

import com.mmdev.dao.UserDao;
import com.mmdev.dto.UserDto;
import com.mmdev.mapper.UserMapper;


public class UserService {

	private final static UserService INSTANCE = new UserService();
	private final UserDao userDao = UserDao.getInstance();
	private final UserMapper userMapper = UserMapper.getInstance();



	private UserService() {
	}

	public void create(UserDto userDto){
		var user = userMapper.map(userDto);
		userDao.create(user);
	}

	public static UserService getInstance() {
		return INSTANCE;
	}
}
