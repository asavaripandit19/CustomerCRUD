package com.account.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.account.demo.model.Customer;
import com.account.demo.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("add")
	public void add(@RequestBody Customer customer) {
		// TODO: process POST request
		customerService.add(customer);
	}
	
	@PostMapping("addAll")
	public void addAll(@RequestBody List<Customer> list) {
		// TODO: process POST request
		customerService.addAll(list);
	}

	@GetMapping("display")
	public ResponseEntity<List<Customer>> display() {
		return customerService.display();
	}
	
	@DeleteMapping("delete/{id}")
	public Customer delete(@PathVariable Integer id) {
		return customerService.delete(id);
	}
	
	@PutMapping("/update/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        customerService.udpdate(customer, id);
    }

	
	
	@GetMapping("search/{id}")
	public Customer search(@PathVariable Integer id) {
		return customerService.search(id);
	}
	
	@GetMapping("searchByMob/{mob}")
	public Customer searchMob(@PathVariable String mob) {
		//TODO: process POST request
		
		return customerService.findMob(mob);
	}
	
	
	

}
