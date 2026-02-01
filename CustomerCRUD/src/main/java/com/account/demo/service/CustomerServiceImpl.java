package com.account.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.account.demo.exception.IdNotFoundException;
import com.account.demo.exception.InvalidCustomerName;
import com.account.demo.exception.InvalidMobileNumber;

import com.account.demo.model.Customer;
import com.account.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	public void validCustomer(Customer customer) {
		
	
		String nm = customer.getName().trim();

		for (int i = 0; i < nm.length(); i++) {
		    if (Character.isDigit(nm.charAt(i))) {
		        throw new InvalidCustomerName("Digits are not allowed in name" );
		    }
		}


	    
	    String mob = customer.getMob().trim();

	    if (mob.length() != 13) {
	        throw new InvalidMobileNumber("Mobile number must be 13 characters");
	    }

	    if (!(mob.startsWith("+91"))) {
	        throw new InvalidMobileNumber("Mobile number must start with +91");
	    }

	    if (mob.charAt(3) < '6') {
	        throw new InvalidMobileNumber("Invalid Indian mobile number");
	    }

	    for (int i = 3; i < mob.length(); i++) {
	        if (!Character.isDigit(mob.charAt(i))) {
	            throw new InvalidMobileNumber("Only digits are allowed after +91");
	        }
	    }

	    
	    if (customerRepository.existsByMob(mob)) {
	        throw new InvalidMobileNumber("Duplicate Mobile Number is not Allowed");
	    }
	
	}

	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
		validCustomer(customer);
		customerRepository.save(customer);

	}

	@Override
	public ResponseEntity<List<Customer>> display() {
		// TODO Auto-generated method stub
		
		List<Customer> customers = customerRepository.findAll();
		
		if(customers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(customers, HttpStatus.OK);

	}

	@Override
	public Customer delete(Integer id) {
		// TODO Auto-generated method stub

		// Serach
		if (customerRepository.findById(id).isPresent()) {
			Customer temp = customerRepository.findById(id).get();
			customerRepository.deleteById(id);
			return temp;
		}
		else {
			throw new IdNotFoundException("ID not found");
		}

		
	}

	@Override
	public void udpdate(Customer customer, Integer id) {
		// TODO Auto-generated method stub
		if(!customerRepository.existsById(id))
			throw new IdNotFoundException("Id not Found!!");
		
		
		customer.setId(id);
		customerRepository.save(customer);

	}

	@Override
	public Customer search(Integer id) {
		// TODO Auto-generated method stub

		if (customerRepository.findById(id).isPresent()) {
			Customer temp = customerRepository.findById(id).get();
			return temp;
		}
		else {
			throw new IdNotFoundException("ID not found");
		}

		
	}

	@Override
	public void addAll(List<Customer> list) {
		// TODO Auto-generated method stub
		for(Customer customer:list) {
			validCustomer(customer);
		}
		customerRepository.saveAll(list);

	}

	@Override
	public Customer findMob(String mob) {
		return customerRepository.findByMob(mob);
	}

}
