package com.cognizant.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.cp.model.ProcessRequest;


public interface ProcessRequestRepository extends JpaRepository<ProcessRequest,Integer> {

	public ProcessRequest findByUserId(int userId);
	
}
