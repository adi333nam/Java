package com.cognizant.rop.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessResponse {

	
	private int requestId;
	private int userId;
	private double processingCharge;
	private double packagingAndDeliveryCharge;
	private LocalDate dateOfDelivery;
}
