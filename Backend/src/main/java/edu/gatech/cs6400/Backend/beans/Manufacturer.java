package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class Manufacturer {
	private String manufacturer;
	private int total_number;
	private double avg_price;
	private double min_price;
	private double max_price;

	public Manufacturer() {
		super();
	}

	public Manufacturer(String manufacturer, int total_number, double avg_price, double min_price, double max_price) {
		super();
		this.manufacturer = manufacturer;
		this.total_number = total_number;
		this.avg_price = avg_price;
		this.min_price = min_price;
		this.max_price = max_price;
	}
}

/*
 * public class Manufacturer { private String manufacturer; private float
 * max_discount;
 * 
 * public Manufacturer(String manufacturer, float max_discount) { super();
 * this.manufacturer = manufacturer; this.max_discount = max_discount; }
 * 
 * public Manufacturer() { super(); } }
 * 
 */