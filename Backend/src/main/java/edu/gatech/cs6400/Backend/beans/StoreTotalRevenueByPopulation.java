package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class StoreTotalRevenueByPopulation {

	private double revenue;
	private int storeID;

	public StoreTotalRevenueByPopulation(double revenue, int storeID) {
		super();
		this.revenue = revenue;
		this.storeID = storeID;
	}

	public StoreTotalRevenueByPopulation() {
		super();
	}

}
