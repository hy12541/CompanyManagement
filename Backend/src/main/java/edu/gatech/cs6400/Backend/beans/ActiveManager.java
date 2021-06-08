package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class ActiveManager {
	private String email;
	private int storeID;

	public ActiveManager(String email, int storeID) {
		super();
		this.email = email;
		this.storeID = storeID;
	}

	public ActiveManager() {
		super();
	}
}