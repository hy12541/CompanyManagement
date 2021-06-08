package edu.gatech.cs6400.Backend.beans;

import java.sql.Date;

import lombok.Data;

@Data // The annotation in lombok used to generate getters and setters
public class Holiday {

	private String holiday_name;
	private Date holiday_date;

	public Holiday() {
		super();
	}

	public Holiday(String holiday_name, Date holiday_date) {
		super();
		this.holiday_name = holiday_name;
		this.holiday_date = holiday_date;
	}

}
