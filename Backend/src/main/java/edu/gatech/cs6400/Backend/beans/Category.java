package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class Category {
	private String categoryName;
	private int num_product;
    private int num_manufacturename;
    private double avg_price;


	public Category() {
		super();
	}


	public Category(String categoryName, int num_product, int num_manufacturename, double avg_price) {
		super();
		this.categoryName = categoryName;
		this.num_product = num_product;
		this.num_manufacturename = num_manufacturename;
		this.avg_price = avg_price;
	}

}