package com.chiragreddy.CustomerManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiragreddy.CustomerManagement.model.Customer;

public interface CustomerDAO extends JpaRepository<Customer,Integer>{ // since we are doing operations by id - save or delete

	
}