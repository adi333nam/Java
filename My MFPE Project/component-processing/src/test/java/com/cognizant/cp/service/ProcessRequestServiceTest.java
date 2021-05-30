package com.cognizant.cp.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.cp.exception.UserNotFoundException;
import com.cognizant.cp.model.ComponentType;
import com.cognizant.cp.model.DefectiveComponentDetails;
import com.cognizant.cp.model.ProcessRequest;
import com.cognizant.cp.repository.ProcessRequestRepository;


@ExtendWith(MockitoExtension.class)
class ProcessRequestServiceTest {

	
	@Mock
	ProcessRequestService processRequestService;
	
	@Mock
	ProcessRequestRepository processRequestRepository;
	
	ProcessRequest processRequest;
	
	@BeforeEach
	void init()
	{
		DefectiveComponentDetails defectiveComponentDetails=new DefectiveComponentDetails(1,ComponentType.ACCESSORY,"Mobile",10);
		processRequest=new ProcessRequest(1,"Adarsh",9879879870l,12345678903555l,defectiveComponentDetails,false);
		
	}
	
	@Test
	void testSaveRequest() {
		processRequestRepository.save(processRequest);
		when(processRequestRepository.getOne(1)).thenReturn(processRequest);
		ProcessRequest actualProcessRequest=processRequestRepository.getOne(1);
		assertEquals(processRequest, actualProcessRequest);
	}


	@Test
	void testGetByUserId() {
		when(processRequestService.getByUserId(1)).thenReturn(processRequest);
		ProcessRequest actualProcessRequest=processRequestService.getByUserId(1);
		assertEquals(processRequest,actualProcessRequest);
		
	}
	
	@Test
	void testGetByUserIdForUserNotFoundException() {
		when(processRequestService.getByUserId(2)).thenThrow(new UserNotFoundException("User does not exits"));
		assertThrows(UserNotFoundException.class,()->processRequestService.getByUserId(2));

		
	}
	
	

}
