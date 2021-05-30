package com.cognizant.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.payment.model.CreditCard;

@Repository
public interface PaymentServiceRepository extends JpaRepository<CreditCard,Long>{

}
