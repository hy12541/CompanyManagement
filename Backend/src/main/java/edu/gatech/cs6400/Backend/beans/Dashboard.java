package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data

public class Dashboard {
	private int count_manager;
	private int count_manufacturer;
	private int count_product;
	private int count_store;

	public Dashboard(int count_manager, int count_manufacturer, int count_product, int count_store) {
		super();
		this.count_manager = count_manager;
		this.count_manufacturer = count_manufacturer;
		this.count_product = count_product;
		this.count_store = count_store;
	}
	
	public Dashboard() {
		super();
	}

}

