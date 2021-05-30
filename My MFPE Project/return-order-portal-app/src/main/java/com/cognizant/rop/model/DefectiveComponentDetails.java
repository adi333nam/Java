package com.cognizant.rop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefectiveComponentDetails {
    
    
	private int componentId;
	private ComponentType componentType;
	private String componentName;
	private int quantity;
	
	public DefectiveComponentDetails(String componentName, ComponentType componentType, int quantity) {
		this.componentName=componentName;
		this.componentType=componentType;
		this.quantity=quantity;
	}
}
