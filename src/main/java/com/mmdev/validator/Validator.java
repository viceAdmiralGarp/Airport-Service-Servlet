package com.mmdev.validator;


import java.util.List;

public interface Validator<U> {
	ValidationResult isValid(U object);
}
