package com.cognizant.cp.factoryservice;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.cp.dao.ProcessService;
import com.cognizant.cp.model.ComponentType;
import com.cognizant.cp.model.ProcessRequest;
import com.cognizant.cp.model.ProcessResponse;
import com.cognizant.cp.service.PackagingAndDeliveryService;
import com.cognizant.cp.service.ProcessRequestService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccessorPartService implements ProcessService {
	

	@Autowired
	ProcessRequestService processRequestService;

	@Autowired
	private PackagingAndDeliveryService packagingAndDeliveryService;
	
	@Override
	public ProcessResponse getProcessResponse(int userId) {
		log.info("Accessor Part Service method getProcessResponse is invoked");
		int processingDays=5;
		double processingCharge=300;
	

		
		ProcessRequest newProcessRequest=processRequestService.getByUserId(userId);
		double packagingCharges=packagingAndDeliveryService.getPackagingAndDeliveryCharges(ComponentType.ACCESSORY,newProcessRequest.getDefectiveComponentDetails().getQuantity()); 	
		
		
		LocalDate currentDay = LocalDate.now();
		ProcessResponse processResponse=new ProcessResponse();
		processResponse.setUserId(userId);
		processResponse.setProcessingCharge(processingCharge);
		processResponse.setDateOfDelivery(currentDay.plusDays(processingDays));
		processResponse.getPackagingAndDeliveryCharge();
		processResponse.setPackagingAndDeliveryCharge(packagingCharges);
		log.info("Accessor Part Service method getProcessResponse is ended");
		return processResponse;
	}

}
