package edu.gatech.cs6400.Backend.beans;

import java.time.LocalDate;

import lombok.Data;

@Data // The annotation in lombok used to generate getters and setters
public class ProductSold {
	private LocalDate sold_date;
	private int quantity;
	private double sold_price;
	private int PID;

	public ProductSold(LocalDate sold_date, int quantity, double sold_price, int PID) {
		super();
		this.sold_date = sold_date;
		this.quantity = quantity;
		this.sold_price = sold_price;
		this.PID = PID;
	}

	public ProductSold() {
		super();
	}
}