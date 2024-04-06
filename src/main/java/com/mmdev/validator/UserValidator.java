package com.mmdev.validator;


import com.mmdev.dto.UserDto;
import com.mmdev.entity.Gender;


public class UserValidator implements Validator<UserDto> {

	private final static UserValidator INSTANCE = new UserValidator();
	private final String EMPTY_FIELD = "";//TODO can be converted to local variable

	private UserValidator() {
	}

	@Override
	public ValidationResult isValid(UserDto userDto) {
		var validationResult = new ValidationResult();
		if (userDto.getName().equals(EMPTY_FIELD)) {
			validationResult.add(Error.of("invalid.Name", "field cannot be empty, please enter Name"));
		}
		if (userDto.getEmail().equals(EMPTY_FIELD)) {
			validationResult.add(Error.of("invalid.Email", "field cannot be empty, please enter Email"));
		}
		if (userDto.getPassword().equals(EMPTY_FIELD)) {
			validationResult.add(Error.of("invalid.Password", "field cannot be empty, please enter Password"));
		}
		if (userDto.getBirthday().equals(EMPTY_FIELD)) {
			validationResult.add(Error.of("invalid.Birthday", "field cannot be empty, please enter Birthday"));
		}
		if (userDto.getGender() == null || Gender.valueOf(userDto.getGender()) == null) {//TODO: Condition 'Gender.valueOf(userDto.getGender()) == null' is always 'false' when reached
			validationResult.add(Error.of("invalid.Gender", "field cannot be empty, please enter Gender"));
		}
		return validationResult;
	}

	public static UserValidator getInstance() {
		return INSTANCE;
	}
}