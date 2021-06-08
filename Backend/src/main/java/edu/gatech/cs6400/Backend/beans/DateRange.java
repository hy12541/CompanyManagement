package edu.gatech.cs6400.Backend.beans;

import java.sql.Date;

import lombok.Data;

@Data
public class DateRange {
	private Date max_date;
	private Date min_date;

	public DateRange() {
		super();
	}

	public DateRange(Date max_date, Date min_date) {
		super();
		this.max_date = max_date;
		this.min_date = min_date;
	}
}
