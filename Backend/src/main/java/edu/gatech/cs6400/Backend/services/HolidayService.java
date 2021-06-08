package edu.gatech.cs6400.Backend.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gatech.cs6400.Backend.beans.Holiday;
import edu.gatech.cs6400.Backend.daos.HolidayDao;

@Service
public class HolidayService {

	@Autowired
	private HolidayDao hd;

	public void addHoliday(Holiday newHoliday) {
		this.hd.addHoliday(newHoliday.getHoliday_date(), newHoliday.getHoliday_name());
	}

	public void deleteHoliday(String deleteHolidayDate) {
		this.hd.deleteHoliday(deleteHolidayDate);
	}

	public List<Holiday> getHolidays() {
		return this.hd.getHolidays();
	}
}
