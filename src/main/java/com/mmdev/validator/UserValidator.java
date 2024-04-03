package com.mmdev.validator;


import com.mmdev.dto.UserDto;
import com.mmdev.entity.Gender;
import com.mmdev.entity.Role;


public class UserValidator implements Validator<UserDto> {

	private final static UserValidator INSTANCE = new UserValidator();
	private final String EMPTY_FIELD = "";

	private UserValidator() {
	}

	@Override
	public ValidationResult isValid(UserDto userDto) {
		var validationResult = new ValidationResult();
		if (userDto.getName() == EMPTY_FIELD) {
			validationResult.add(Error.of("invalid.Name", "Name is invalid"));
		}
		if (userDto.getEmail() == EMPTY_FIELD) {
			validationResult.add(Error.of("invalid.Email", "Email is invalid"));
		}
		if (userDto.getPassword() == EMPTY_FIELD) {
			validationResult.add(Error.of("invalid.Password", "Password is invalid"));
		}
		if (userDto.getBirthday() == EMPTY_FIELD) {
			validationResult.add(Error.of("invalid.Birthday", "Birthday is invalid"));
		}
		if (userDto.getGender() == null || Gender.valueOf(userDto.getGender()) == null) {
			validationResult.add(Error.of("invalid.Gender", "Gender is invalid"));
		}
		return validationResult;
	}

	public static UserValidator getInstance() {
		return INSTANCE;
	}
}