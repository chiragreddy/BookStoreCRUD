package com.chiragreddy.CustomerManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiragreddy.CustomerManagement.dao.CustomerDAO;
import com.chiragreddy.CustomerManagement.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	CustomerDAO custdao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/getCustomer")
	public List<Customer> getCustomers() {
		return custdao.findAll();
	}
	
	@RequestMapping("/getCustomer/{id}")
	public Customer getCustomer(@PathVariable("id") int id) {
		return custdao.findById(id).orElse(new Customer("Doesn't Exist", 200)); // can return a customer name as string or the entity or object itself with values passed in constructor
	}
	
	@RequestMapping("/searchCustomer/{id}")
	public Customer searchCustomer(@PathVariable("id") int id) {
		return custdao.findById(id).orElse(new Customer("Page Not Found", 404)); 
	}
	
	@RequestMapping("/saveCustomer")         // use as /saveCustomer/?id=10&name=chirag
	public Customer createCustomer( Customer customer) { // when saving or updating, we can add the whole entity or the customer object
		custdao.save(customer);
		return customer;
	}
	
	@RequestMapping("/updateCustomer")
	public Customer updateCustomer(Customer customer) {        // the id and name of can be obtained from the entity itself
		custdao.deleteById(customer.getId());
		custdao.save(customer);                   // update - delete existing customer by id and create new customer
		return customer;
	}
	
	@RequestMapping("/deleteCustomer/{id}")
	public Customer deleteCustomer(@PathVariable("id") int id) {
		custdao.deleteById(id);
		return new Customer("Customer with id " + id + " is deleted", 200);
	}
	
}
