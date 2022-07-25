package com.cts.pension.disbursement.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<String> handleAuthorizationException(AuthorizationException AuthorizationException){
		return new ResponseEntity<String>("no such inputs", HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<String> handleMissingRequestHeaderException(MissingRequestHeaderException MissingRequestHeaderException){
		return new ResponseEntity<String>("invalid input given", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AadharNumberNotFound.class)
	public ResponseEntity<String> handleMissingRequestHeaderException(AadharNumberNotFound AadharNumberNotFound ){
		return new ResponseEntity<String>("request not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException FeignException, HttpServletResponse response) {
		return new ResponseEntity<String>("no such data", HttpStatus.BAD_REQUEST);
    }
}
