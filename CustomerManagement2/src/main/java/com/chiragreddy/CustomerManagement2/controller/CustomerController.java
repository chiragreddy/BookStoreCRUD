package com.chiragreddy.CustomerManagement2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiragreddy.CustomerManagement2.dao.CustomerDAO;
import com.chiragreddy.CustomerManagement2.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	CustomerDAO custdao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(path = "/getCustomer", produces = {"application/json", "application/xml"})
	public List<Customer> getCustomers() {
		return custdao.findAll();
	}
	
	@RequestMapping(path = "/getCustomer/{id}", produces = {"application/json", "application/xml"})
	public Customer getCustomer(@PathVariable("id") int id) {
		return custdao.findById(id).orElse(new Customer("Doesn't Exist", 200)); // can return a customer name as string or the entity or object itself with values passed in constructor
	}
	
	@GetMapping(path = "/searchCustomer/{id}", produces = "application/json")
	public Customer searchCustomer(@PathVariable("id") int id) {
		return custdao.findById(id).orElse(new Customer("Page Not Found", 404)); 
	}
	
	// only post and put require "consumes" as we give the request body
	
	@PostMapping(path = "/saveCustomer", produces = "application/json", consumes = {"application/xml","application/json"})         // the requestBody here can be passed in Postman for save/update operation
	public Customer createCustomer(@RequestBody Customer customer) { // when saving or updating, we can add the whole entity or the customer object
		custdao.save(customer);
		return customer;
	}
	
	@PutMapping(path ="/updateCustomer", produces = "application/json", consumes = {"application/xml","application/json"})
	public Customer updateCustomer(@RequestBody Customer customer) {        // the id and name of can be obtained from the entity itself
		custdao.deleteById(customer.getId());
		custdao.save(customer);                   // update - delete existing customer by id and create new customer
		return customer;
	}
	
	@DeleteMapping(path = "/deleteCustomer/{id}", produces = "application/json") 
	public Customer deleteCustomer(@PathVariable("id") int id) {
		custdao.deleteById(id);
		return new Customer("Customer with id " + id + " is deleted", 200);
	}
	
}