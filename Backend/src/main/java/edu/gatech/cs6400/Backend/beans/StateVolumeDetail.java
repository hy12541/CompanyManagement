package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class StateVolumeDetail {
	private String city;
	private String address;
	private int storeID;
	private String manager_name;
	private String email;
	public StateVolumeDetail( String state_name,String email, int max_units_sold, String city, String address, int storeID, String manager_name) {
		super();
		this.city         = city      ;  
	    this.address      = address    ; 
	    this.storeID      = storeID    ; 
	    this.manager_name = manager_name;
	    this.email=email;
	}
	
	public StateVolumeDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

}
