package com.cognizant.cp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.cp.dao.ProcessService;
import com.cognizant.cp.dto.ProcessRequestDto;
import com.cognizant.cp.exception.ComponentTypeNotFoundException;
import com.cognizant.cp.exception.UserNotFoundException;
import com.cognizant.cp.factoryservice.ComponentFactory;
import com.cognizant.cp.model.ComponentType;
import com.cognizant.cp.model.ProcessRequest;
import com.cognizant.cp.model.ProcessResponse;
import com.cognizant.cp.repository.ProcessRequestRepository;
import com.cognizant.cp.repository.ProcessResponseRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessResponseService {

	@Autowired
	private ProcessResponseRepository processResponseRepository;
	
	@Autowired
	private ProcessRequestRepository processRequestRepository;
	@Autowired
	ComponentFactory factory;

	
	public void saveResponse(ProcessResponse processResponse)
	{
		log.info("ProcessResponseService method saveResponse is invoked");
		processResponseRepository.save(processResponse);
	}
	
	public ProcessResponse getProcessResponseByRequestId(int requestId)
	{
		log.info("ProcessResponseService method getProcessResponseByRequestId is invoked");
		if(!processResponseRepository.existsById(requestId))
		{
			throw new UserNotFoundException("Request ID : "+requestId+" does not exists");
		}
		log.info("ProcessResponseService method getProcessResponseByRequestId is ended");
		return processResponseRepository.getOne(requestId);
	}
	
	public ProcessRequest processRequestToDto(ProcessRequestDto processRequestDto)
	{
		log.info("ProcessResponseService method processRequestToDto is invoked");
		ProcessRequest processRequest=new ProcessRequest();
		processRequest.setUserId(processRequestDto.getUserId());
		processRequest.setUserName(processRequestDto.getUserName());
		processRequest.setContactNumber(processRequestDto.getContactNumber());
		processRequest.setDefectiveComponentDetails(processRequestDto.getDefectiveComponentDetails());
		processRequest.setCreditCardNumber(processRequestDto.getCreditCardNumber());
		processRequest.setPriorityRequest(processRequestDto.isPriorityRequest());
		log.info("ProcessResponseService method processRequestToDto is ended");
		return processRequest;
	}
	
	@Transactional
	public ProcessResponse getResponse(ProcessRequestDto processRequestDto)
	{
		log.info("ProcessResponseService method getResponse is invoked");
		ProcessRequest processRequest=processRequestToDto(processRequestDto);
		
		ComponentType componentType=processRequest.getDefectiveComponentDetails().getComponentType();
		String component=componentType.toString();
		if(!(component.equals("ACCESSORY") || component.equals("INTEGRAL")))
		{
			throw new ComponentTypeNotFoundException("ComponentType should be either INTEGRAL or ACCESSORY");
		}
		ProcessRequest saveRequest=processRequestRepository.save(processRequest);
		componentType=saveRequest.getDefectiveComponentDetails().getComponentType();
		int userId=saveRequest.getUserId();
		log.info("User Id :-"+userId);
		ProcessResponse processResponse=getResponseForComponent(componentType,userId);
	    processResponseRepository.save(processResponse);
	    log.info("ProcessResponseService method getResponse is ended");
		return processResponse;
		
	}
	
	public ProcessResponse getResponseForComponent(ComponentType componentType,int userId)
	{
		log.info("ProcessResponseService method getResponseForComponent is invoked");
		ProcessService processService=factory.getComponentFactory(componentType);
		log.info("ProcessResponseService method getResponseForComponent is ended");
		return processService.getProcessResponse(userId);
	}
	
	
}
