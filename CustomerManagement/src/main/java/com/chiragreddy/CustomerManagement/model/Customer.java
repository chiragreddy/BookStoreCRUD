package com.chiragreddy.CustomerManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

	private String name;
	@Id
	private int id;
	
	public Customer() {
		
	}
	
	public Customer(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "id: " + id + ", name: " + name;
	}
	
}
