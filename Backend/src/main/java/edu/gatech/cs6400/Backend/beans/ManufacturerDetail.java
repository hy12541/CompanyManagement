package edu.gatech.cs6400.Backend.beans;

import java.util.List;

import lombok.Data;

@Data
public class ManufacturerDetail {

	private String manufacturerName;
	private double maxDiscount;
	private List<ManufacturerProduct> manufacturerProducts;


	public ManufacturerDetail() {
		super();
	}


	public ManufacturerDetail(String manufacturerName, double maxDiscount,
			List<ManufacturerProduct> manufacturerProducts) {
		super();
		this.manufacturerName = manufacturerName;
		this.maxDiscount = maxDiscount;
		this.manufacturerProducts = manufacturerProducts;
	}

}
