package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class StateVolume {
	private String category;
	private String state_name;
    private int max_units_sold;
    
	public StateVolume() {
		super();
	}
	
	public StateVolume(String category, String state_name, int max_units_sold) {
		super();
		this.category = category;
		this.state_name = state_name;
		this.max_units_sold = max_units_sold;
	}
}
