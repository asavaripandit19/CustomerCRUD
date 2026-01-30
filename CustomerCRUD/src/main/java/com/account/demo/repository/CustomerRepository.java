package com.account.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	

	Customer findByMob(String mob);

	boolean existsByMob(String mob);
	
	

}