package com.courseenrollment.exception;

public class EmptyObjects extends RuntimeException {
	@Override
	public String getMessage() {
		return  "Curently No Data Is Avilable in Database";
	}
}
