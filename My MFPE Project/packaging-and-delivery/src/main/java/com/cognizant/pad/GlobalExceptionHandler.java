package com.cognizant.pad;


import java.util.Date;
import java.util.LinkedHashMap;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.pad.exception.ComponentTypeNotFoundException;
import com.cognizant.pad.exception.InvalidQuantityException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	
	private static final String ERROR="error";
	private static final String TIMESTAMP="timestamp";
	
	@ExceptionHandler(InvalidQuantityException.class)
	protected ResponseEntity<Object> handleInavlidQuantityException(InvalidQuantityException exception) {
		
		log.info("Start handling InvalidQuantityException");
		Map<String,Object> body=new LinkedHashMap<>();
		body.put(TIMESTAMP,new Date());
		String errors=exception.getMessage();
		body.put(ERROR,errors);
		log.info("End handling InvalidQuantityException");
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ComponentTypeNotFoundException.class)
	protected ResponseEntity<Object> handleComponentDoesNotExitException(ComponentTypeNotFoundException exception) {
		
		log.info("Start handling ComponentDoesNotExitException");
		Map<String,Object> body=new LinkedHashMap<>();
		body.put(TIMESTAMP,new Date());
		String errors=exception.getMessage();
		body.put(ERROR,errors);
		log.info("End handling ComponentDoesNotExitException");
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleInvalidException(MethodArgumentTypeMismatchException exception){
		
		log.info("Start handling MethodArgumentTypeMismatchException");
		Map<String,Object> body=new LinkedHashMap<>();
		body.put(TIMESTAMP,new Date());
		String errors="Wrong Method Argument Type. It should be INTEGRAL,ACCESSORY and another one is number betwwn 1 to 100";
		body.put(ERROR,errors);
		log.info("End Start handling MethodArgumentTypeMismatchException");
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
}

