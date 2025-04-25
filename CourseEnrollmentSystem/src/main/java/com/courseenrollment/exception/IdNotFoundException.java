package com.courseenrollment.exception;

public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return  "Id Not Found In DataBase";
	}
}
