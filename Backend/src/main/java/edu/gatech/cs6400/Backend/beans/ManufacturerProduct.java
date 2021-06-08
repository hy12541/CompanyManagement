package edu.gatech.cs6400.Backend.beans;

import java.util.List;

import lombok.Data;

@Data
public class ManufacturerProduct {

	private int productPID;
	private String productName;
	private double productPrice;
	private String productCategories;
	public ManufacturerProduct() {
		super();
	}
	public ManufacturerProduct(int productPID, String productName, double productPrice, String productCategories) {
		super();
		this.productPID = productPID;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCategories = productCategories;
	}
	
	
}
