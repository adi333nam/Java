package com.cognizant.cp.dto;


import com.cognizant.cp.model.DefectiveComponentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessRequestDto {
	
	private int userId;

	private String userName;

	private long contactNumber;

	private long creditCardNumber;
	private DefectiveComponentDetails defectiveComponentDetails;
	private boolean isPriorityRequest;
}
