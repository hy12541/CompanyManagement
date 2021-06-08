package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class Manager {
	private String email;
	private String manager_name;
	private Integer stores;

	public Manager(String email, String manager_name,Integer stores) {
		super();
		this.email = email;
		this.manager_name = manager_name;
		this.stores=stores;
		
	}

	public Manager() {
		super();
	}
}
