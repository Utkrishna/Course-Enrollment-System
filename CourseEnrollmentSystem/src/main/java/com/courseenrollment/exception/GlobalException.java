package com.courseenrollment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.courseenrollment.dto.ResponseStructure;
@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handelIdNotFoundExcption(IdNotFoundException exp){
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("failure");
		response.setData(exp.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyObjects.class)
	public ResponseEntity<ResponseStructure<String>> handelEmptyObjectsExcption(EmptyObjects exp){
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage(" No Data available");
		response.setData(exp.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
}
