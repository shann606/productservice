package com.ecom.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = 8554286090230784738L;

	@ExceptionHandler(exception = Exception.class)
	ResponseEntity<ExceptionDTO> handleException(Exception ex) {

		ExceptionDTO exe = new ExceptionDTO("Failed", ex.getMessage());

		return new ResponseEntity<>(exe, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
