package com.mmdev.exception;

public class DaoException extends RuntimeException{
	public DaoException(){
		throw new DaoException();
	}
}
