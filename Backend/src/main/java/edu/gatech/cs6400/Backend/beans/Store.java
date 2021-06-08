package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class Store {
	private int storeID;
	private String city;
	private String state_name;
	private String address;
	private int phone;

	public Store(int storeID, String city, String address, String state_name, int phone) {
		super();
		this.storeID = storeID;
		this.state_name = state_name;
		this.city = city;
		this.phone = phone;
		this.address = address;
	}

	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

}
