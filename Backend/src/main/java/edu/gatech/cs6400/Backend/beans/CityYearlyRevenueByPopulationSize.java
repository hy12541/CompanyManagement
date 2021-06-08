package edu.gatech.cs6400.Backend.beans;

import lombok.Data;

@Data
public class CityYearlyRevenueByPopulationSize {
	private int year;
	private double smallCity;
	private double mediumCity;
	private double largeCity;
	private double extraLargeCity;

	public CityYearlyRevenueByPopulationSize() {
		super();
	}

	public CityYearlyRevenueByPopulationSize(int year, double smallCity, double mediumCity, double largeCity,
			double extraLargeCity) {
		super();
		this.year = year;
		this.smallCity = smallCity;
		this.mediumCity = mediumCity;
		this.largeCity = largeCity;
		this.extraLargeCity = extraLargeCity;
	}

}
