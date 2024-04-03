package com.mmdev.exception;

import lombok.Getter;
import com.mmdev.validator.Error;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

	private final List<Error> errors;

	public ValidationException(List<Error> errors) {
		this.errors = errors;
	}
}
