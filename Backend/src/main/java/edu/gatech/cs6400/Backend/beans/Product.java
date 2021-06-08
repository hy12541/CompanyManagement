package edu.gatech.cs6400.Backend.beans;

import java.util.List;

import lombok.Data;

@Data // The annotation in lombok used to generate getters and setters
public class Product {
	private int PID;
	private String product_name;
	private double price;
	private String manufacturer;

	public Product() {
		super();
	}

	public Product(int pID, String product_name, double price, String manufacturer) {
		super();
		PID = pID;
		this.product_name = product_name;
		this.price = price;
		this.manufacturer = manufacturer;
	}

}
