package com.cognizant.rop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProcessRequest {
  
    private int userId;
	private String userName;
	private long contactNumber;
	private long creditCardNumber;
	private DefectiveComponentDetails defectiveComponentDetails;
	private boolean isPriorityRequest;
	
	public ProcessRequest(String userName,long contactNumber,long creditCardNumber,
			String componentName,ComponentType componentType,int quantity,boolean isPriorityRequest)
	{
		this.userName=userName;
		this.contactNumber=contactNumber;
		
		this.creditCardNumber=creditCardNumber;
		
		this.defectiveComponentDetails=new DefectiveComponentDetails(componentName,componentType,quantity);
		this.isPriorityRequest=isPriorityRequest;
		
	}
}
