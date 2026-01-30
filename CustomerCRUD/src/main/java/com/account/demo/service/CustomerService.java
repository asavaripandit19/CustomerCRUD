package com.account.demo.service;

import java.util.List;

import com.account.demo.model.Customer;

public interface CustomerService {

	void add(Customer customer);
	List<Customer> display();
	Customer delete(Integer id);
	void udpdate(Customer customer,Integer id );
	Customer search(Integer id);
	void addAll(List<Customer> list);
	Customer findMob(String mob);
}
