package com.mmdev.serivce;

import com.mmdev.dao.UserDao;
import com.mmdev.dto.UserDto;
import com.mmdev.entity.User;
import com.mmdev.exception.ValidationException;
import com.mmdev.mapper.UserMapper;
import com.mmdev.validator.UserValidator;



public class UserService {

	private final static UserService INSTANCE = new UserService();
	private final UserDao userDao = UserDao.getInstance();
	private final UserMapper userMapper = UserMapper.getInstance();
	private final UserValidator userValidator = UserValidator.getInstance();


	private UserService() {
	}

	public Long create(UserDto userDto) {
		var validationResult = userValidator.isValid(userDto);
		if (!validationResult.isValid()) {
			throw new ValidationException(validationResult.getErrors());
		}
		var userEntity = userMapper.map(userDto);
		userDao.create(userEntity);
		return userEntity.getId();
	}

	public static UserService getInstance() {
		return INSTANCE;
	}
}
