package com.sahooexpress.exception;

import java.time.LocalDateTime;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorAPI> handleException (ResourceNotFoundException ex) {
		
		var errorApi = new ErrorAPI();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMsg(ex.getMessage());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<>(errorApi,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorAPI> handleException(Exception ex) {
		
		var errorApi = new ErrorAPI();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMsg(ex.getMessage());
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		return new ResponseEntity<>(errorApi,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorAPI> handleException(MethodArgumentNotValidException ex) {
		String errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(Obj -> Obj.getField()+ ":"+Obj.getDefaultMessage())
				.collect(Collectors.joining(","));
		
		var errorApi = new ErrorAPI();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setMsg(errors);
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<>(errorApi,HttpStatus.BAD_REQUEST);
	}

}
