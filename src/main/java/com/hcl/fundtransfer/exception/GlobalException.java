package com.hcl.fundtransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ErrormessageException.class)
public  ResponseEntity<ErrormessageException>ErrormessageException(ErrormessageException ex){
		ErrormessageException error= new ErrormessageException(ex.getMessage());
 	return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
}
}