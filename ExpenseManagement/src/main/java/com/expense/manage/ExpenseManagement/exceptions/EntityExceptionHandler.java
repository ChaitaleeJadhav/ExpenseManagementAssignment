package com.expense.manage.ExpenseManagement.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class EntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomError> handleAllExceptions(Exception ex, WebRequest request) {
		CustomError errorObj = new CustomError();
		errorObj.setError(ex.getMessage());
		errorObj.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CustomError>(errorObj, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}
