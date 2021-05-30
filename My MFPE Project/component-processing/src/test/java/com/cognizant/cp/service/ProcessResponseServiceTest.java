package com.cognizant.cp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.cp.dto.ProcessRequestDto;
import com.cognizant.cp.exception.ComponentTypeNotFoundException;
import com.cognizant.cp.exception.RequestIdNotFoundException;
import com.cognizant.cp.model.ComponentType;
import com.cognizant.cp.model.DefectiveComponentDetails;
import com.cognizant.cp.model.ProcessRequest;
import com.cognizant.cp.model.ProcessResponse;
import com.cognizant.cp.repository.ProcessRequestRepository;
import com.cognizant.cp.repository.ProcessResponseRepository;
@ExtendWith(MockitoExtension.class)
class ProcessResponseServiceTest {

	@Mock
	private ProcessRequestService processRequestService;
	
	@Mock
	private ProcessResponseService processResponseService;
	
	@Mock
	private ProcessRequestRepository processRequestRepository;
	
	@Mock
	private ProcessResponseRepository processResponseRepository;
	
	private ProcessRequest processRequest;
	
	private ProcessResponse processResponse;
	
	private ProcessRequestDto processRequestDto;
	
	@BeforeEach
	void init()
	{
		DefectiveComponentDetails defectiveComponentDetails=new DefectiveComponentDetails(1,ComponentType.ACCESSORY,"Mobile",10);
		
		processRequest=new ProcessRequest(1,"Aditya",9879879870l,12345678903555l,defectiveComponentDetails,false);
		processRequestDto=new ProcessRequestDto(1,"Aditya",9879879870l,12345678903555l,defectiveComponentDetails,false);
		
		processResponse=new ProcessResponse(1,1,100.0,3000.0,LocalDate.now());
	}
	
	@Test
	void testSaveResponse() {
		processResponseRepository.save(processResponse);
		when(processResponseRepository.getOne(1)).thenReturn(processResponse);
		ProcessResponse actualProcessResponse=processResponseRepository.getOne(1);
		assertEquals(processResponse, actualProcessResponse);
	}

	@Test
	void testGetProcessResponseByRequestId() {
		when(processResponseRepository.getOne(1)).thenReturn(processResponse);
		ProcessResponse actualProcessResponse=processResponseRepository.getOne(1);
		assertEquals(processResponse, actualProcessResponse);
	}

	@Test
	void testGetProcessResponseByRequestIdForRequestIdNotFoundException() {
		when(processResponseService.getProcessResponseByRequestId(2)).thenThrow(new RequestIdNotFoundException("Request Id does not exits"));
		assertThrows(RequestIdNotFoundException.class,()->processResponseService.getProcessResponseByRequestId(2));
		
	}
	
	@Test
	void testGetResponse() {
		when(processResponseService.getResponse(processRequestDto)).thenReturn(processResponse);
		ProcessResponse actualProcessResponse=processResponseService.getResponse(processRequestDto);
		assertEquals(processResponse,actualProcessResponse);
	}
	
	@Test
	void testGetResponseForException() {
		DefectiveComponentDetails defectiveComponentDetails=new DefectiveComponentDetails(1,ComponentType.PROTECTIVE_SHEALTH,"Mobile",10);
		processRequest=new ProcessRequest(1,"Aditya",9879879870l,12345678903555l,defectiveComponentDetails,false);
		processResponse=new ProcessResponse(1,1,100.0,3000.0,LocalDate.now());
		
		when(processResponseService.getResponse(processRequestDto)).thenThrow(new ComponentTypeNotFoundException("ComponetTypeNotFound"));
		assertThrows(ComponentTypeNotFoundException.class,()->processResponseService.getResponse(processRequestDto));
		
		
	}


	@Test
	void testGetResponseForComponent() {
		
		when(processResponseService.getResponseForComponent(ComponentType.ACCESSORY, 1)).thenReturn(processResponse);
		ProcessResponse actualProcessResponse=processResponseService.getResponseForComponent(ComponentType.ACCESSORY, 1);
		assertEquals(processResponse,actualProcessResponse);
		
	}
	
	@Test
	void testGetResponseForComponentForTypeNotFound() {
		
		when(processResponseService.getResponseForComponent(ComponentType.PROTECTIVE_SHEALTH, 1)).thenThrow(new ComponentTypeNotFoundException("ComponetTypeNotFound"));
		assertThrows(ComponentTypeNotFoundException.class,()->processResponseService.getResponseForComponent(ComponentType.PROTECTIVE_SHEALTH,1));
		
		
	}
	
	

}
