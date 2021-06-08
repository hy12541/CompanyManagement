package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class ActVsPreRev {
	private int PID;
	private String product_name;
	private double price;
	private int total_quantity;
	private int sale_quantity;
	private int non_sale_quantity;
	private double actural_revenue;
	private double predicted_revenue;
	private double difference;

	public ActVsPreRev(int PID, String product_name, double price, int total_quantity, int sale_quantity,
			int non_sale_quantity, double actural_revenue, double predicted_revenue, double difference) {
		super();
		this.PID = PID;
		this.product_name = product_name;
		this.price = price;
		this.total_quantity = total_quantity;
		this.sale_quantity = sale_quantity;
		this.non_sale_quantity = non_sale_quantity;
		this.actural_revenue = actural_revenue;
		this.predicted_revenue = predicted_revenue;
		this.difference = difference;
	}

	public ActVsPreRev() {
		super();
	}
}
