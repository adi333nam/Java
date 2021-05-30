package com.cognizant.payment.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard 
{
	@Id
	private long creditCardNumber;
	private double creditCardLimit;
	
}
