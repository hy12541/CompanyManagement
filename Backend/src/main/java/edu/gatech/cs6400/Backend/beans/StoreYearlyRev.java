package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class StoreYearlyRev {
	private int storeID;
	private String address;
	private String city;
	private int sold_year;
	private double total_revenue;

	public StoreYearlyRev(int storeID, String address, String city, int sold_year, double total_revenue) {
		super();
		this.address = address;
		this.city = city;
		this.total_revenue = total_revenue;
		this.storeID = storeID;
		this.sold_year = sold_year;
	}

	public StoreYearlyRev() {
		super();
	}
}
