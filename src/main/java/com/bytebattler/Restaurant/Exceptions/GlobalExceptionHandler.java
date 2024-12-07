package com.bytebattler.Restaurant.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	public ResponseEntity<String> handleOptimisticLockingFailure(ObjectOptimisticLockingFailureException ex) {
		return new ResponseEntity<>("Conflict: The data was updated or deleted by another transaction. Please retry.", HttpStatus.CONFLICT);
	}
}
