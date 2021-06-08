package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class Groundhog {
	private int sold_year;
	private int total_num;
	private double avg_num;
	private int groundhog_num;
	
	public Groundhog(int sold_year, int total_num, double avg_num, int groundhog_num) {
		super();
		this.sold_year = sold_year;
		this.total_num = total_num;
		this.avg_num = avg_num;
		this.groundhog_num = groundhog_num;
	}
	
	public Groundhog() {
		super();
	}
	

}
