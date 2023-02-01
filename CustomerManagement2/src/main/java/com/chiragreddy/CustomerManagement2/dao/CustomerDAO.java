package com.chiragreddy.CustomerManagement2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiragreddy.CustomerManagement2.model.Customer;

public interface CustomerDAO extends JpaRepository<Customer,Integer>{ // since we are doing operations by id - save or delete

	
}