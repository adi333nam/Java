package com.cognizant.cp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.cp.exception.UserNotFoundException;
import com.cognizant.cp.model.ProcessRequest;
import com.cognizant.cp.repository.ProcessRequestRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessRequestService {

	@Autowired
	private ProcessRequestRepository processRequestRepository;
		
	public void saveRequest(ProcessRequest processRequest)
	{
		log.info("ProcessRequestService method saveRequest is invoked");
		processRequestRepository.save(processRequest);
		log.info("ProcessRequestService method saveRequest is ended");
	}
	
	
	public ProcessRequest getByUserId(int userId)
	{
		log.info("ProcessRequestService method getByUserId is invoked");
		if(!processRequestRepository.existsById(userId))
		{
			throw new UserNotFoundException("User with Id "+userId+" does not exists");
		}
		log.info("ProcessRequestService method getRequestById is ended");
		return processRequestRepository.findByUserId(userId);
	}

}
