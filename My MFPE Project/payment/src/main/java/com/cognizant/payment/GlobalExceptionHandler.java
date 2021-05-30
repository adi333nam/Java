package com.cognizant.payment;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.payment.exception.InvalidCreditCardNumberException;
import com.cognizant.payment.exception.InvalidProcessingChargeException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	private static final String ERROR="error";
	private static final String TIMESTAMP="timestamp";
	
	@ExceptionHandler(InvalidCreditCardNumberException.class)
	protected ResponseEntity<Object> handleInavlidCreditCardNumberException(InvalidCreditCardNumberException exception) {
		
		log.info("Start handling InavlidCreditCardNumberException");
		Map<String,Object> body=new LinkedHashMap<>();
		body.put(TIMESTAMP,new Date());
		String errors=exception.getMessage();
		body.put(ERROR,errors);
		log.info("End handling InavlidQuantityException");
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidProcessingChargeException.class)
	protected ResponseEntity<Object> handleInvalidProcessingChargeException(InvalidProcessingChargeException exception) {
		
		log.info("Start handling InvalidProcessingChargeException");
		Map<String,Object> body=new LinkedHashMap<>();
		body.put(TIMESTAMP,new Date());
		String errors=exception.getMessage();
		body.put(ERROR,errors);
		log.info("End handling InvalidProcessingChargeException");
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleInvalidException(MethodArgumentTypeMismatchException exception){
		
		log.info("Start handling InvalidException");
		Map<String,Object> body=new LinkedHashMap<>();
		body.put(TIMESTAMP,new Date());
		String errors="Wrong Method Argument Type";
		body.put(ERROR,errors);
		log.info("End handling Invalid Exception");
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
}

