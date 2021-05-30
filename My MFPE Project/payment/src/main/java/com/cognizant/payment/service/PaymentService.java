package com.cognizant.payment.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.payment.exception.InvalidCreditCardNumberException;
import com.cognizant.payment.exception.InvalidProcessingChargeException;
import com.cognizant.payment.model.CreditCard;
import com.cognizant.payment.repository.PaymentServiceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	@Autowired
	private PaymentServiceRepository paymentServiceRepository;
	
	@PostConstruct
	public void init()
	{
		saveCreditCardDetails(new CreditCard(378282246310005l,1000));
		saveCreditCardDetails(new CreditCard(5105105105105100l,4000));
		saveCreditCardDetails(new CreditCard(371449635398431l,1000));
		saveCreditCardDetails(new CreditCard(4012888888881881l,4000));
	
	}

	public double processPayment(long creditCardNumber, double creditLimit, double processingCharge) throws InvalidProcessingChargeException, InvalidCreditCardNumberException {

		log.info("Payment Service :- processPayement called");
		
		double currentBalance = 0;
		
		try {
			
			if(!isValidCardNumber(creditCardNumber))
			{
				throw new InvalidCreditCardNumberException();
			}
			
			saveCreditCardDetails(new CreditCard(creditCardNumber,creditLimit));
			
			if (isValidProcessingCharge(processingCharge) && creditLimit>=processingCharge ) {
				currentBalance = creditLimit - processingCharge;
			} else {
				throw new InvalidProcessingChargeException("Invalid Processing Charge "+processingCharge);
			}
		}
		catch(InvalidProcessingChargeException exception)
		{
			throw new InvalidProcessingChargeException("Invalid Processing Charge "+processingCharge);
		}
		catch(Exception e)
		{
			throw new InvalidCreditCardNumberException("Invalid Credit Card "+creditCardNumber);
		}
		
		log.info("Payment Service :- processPayement end");
		
		return currentBalance;


	}
	
	public boolean isValidCardNumber(long creditCardNumber) {
		
		log.info("Payment service :- isValidCardNumber called");
		boolean check=false;
		String card=Long.toString(creditCardNumber);
		int totalDigits=card.length();
		int i=0;
		int oddSum=0;
		int evenSum=0;
		while(creditCardNumber!=0)
		{
			int digit=(int)(creditCardNumber%10);
			if(i%2==0)
			{
				oddSum+=digit;
			}
			else
			{
				digit=digit*2;
				if(digit>9)
				{
					int temporary=digit;
					int tempSum=0;
					while(temporary!=0)
					{
						int temp=temporary%10;
						tempSum+=temp;
						temporary=temporary/10;
					}
					digit=tempSum;
				}
				evenSum+=digit;
			}
			creditCardNumber=creditCardNumber/10;
			i++;	
		}
		int totalSum=oddSum+evenSum;
		
		if(totalSum%10==0)
		{
			check=true;
		}
		if(totalDigits<13 || totalDigits>19)
		{
			check=false;
		}
		log.info("Payment service :- isValidCardNumber end");
		return check;
	}

	public boolean isValidProcessingCharge(double processingCharge)
	{
		log.info("PaymentService :- isValidProcessingCharge called");
		boolean check=false;
		if(processingCharge>=0)
		{
			check=true;
		}
		
		log.info("PaymentService :- isValidProcessingCharge end");
		return check;
	}
	
	public void saveCreditCardDetails(CreditCard creditCard)
	{
		 paymentServiceRepository.save(creditCard);
	}

}
