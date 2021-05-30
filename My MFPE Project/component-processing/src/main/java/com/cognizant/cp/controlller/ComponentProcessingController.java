package com.cognizant.cp.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cp.dto.ProcessRequestDto;
import com.cognizant.cp.model.ProcessRequest;
import com.cognizant.cp.model.ProcessResponse;
import com.cognizant.cp.service.AuthorizationService;
import com.cognizant.cp.service.PaymentService;
import com.cognizant.cp.service.ProcessRequestService;
import com.cognizant.cp.service.ProcessResponseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/component-processing")
public class ComponentProcessingController {

	
	@Autowired
	private ProcessResponseService processResponseService;
	
	@Autowired
	private ProcessRequestService processRequestService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	private static final String TOKEN="--------------------------TOKEN:::";
	
	@PostMapping("/processDetail")
	@ResponseStatus(code=HttpStatus.CREATED)
	public ProcessResponse getProcessDetails(@RequestHeader("Authorization") String token,@RequestBody ProcessRequestDto processRequestDto) throws com.fasterxml.jackson.databind.exc.InvalidFormatException
	{
		log.info("GetProcessDetail method get invoked");
		log.info(TOKEN+token);
		boolean valid=authorizationService.validateHeaderToken(token);
		
		if(!valid)
		{
			return null;
		}
		return processResponseService.getResponse(processRequestDto) ;
	}

	
	
	
	@PostMapping("/completeProcessing/{requestId}/{creditCardNumber}/{creditLimit}/{processingCharge}")
	@ResponseStatus(code=HttpStatus.CREATED)
	public double doCompleteProcessing(
			@RequestHeader("Authorization") String token,
		@PathVariable("requestId") int requestId,
		@PathVariable("creditCardNumber") long creditCardNumber,
		@PathVariable("creditLimit") double creditLimit,
		@PathVariable("processingCharge") double processingCharge)
	{
		log.info("doCompleteProcessing method get invoked");
		log.info(TOKEN+token);
		boolean valid=authorizationService.validateHeaderToken(token);
	
		
		if(!valid)
		{
			return -1;
		}
		return paymentService.getPayment(creditCardNumber, creditLimit, processingCharge);	
	}

	@PostMapping("/getRequest/{userId}")
	@ResponseStatus(code=HttpStatus.CREATED)
	public ProcessRequest getRequest(@RequestHeader("Authorization") String token,@PathVariable("userId") int userId)
	{
		log.info("getRequest method get invoked");
		log.info(TOKEN+token);
		boolean valid=authorizationService.validateHeaderToken(token);
		
		if(!valid)
		{
			return null;
		}
	return processRequestService.getByUserId(userId);	
	}
	
}
