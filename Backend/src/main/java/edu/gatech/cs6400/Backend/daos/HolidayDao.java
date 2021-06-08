package edu.gatech.cs6400.Backend.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.Holiday;

@Repository
public class HolidayDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Holiday> getHolidays() {
		return this.jdbcTemplate.query("SELECT * FROM Holiday", new RowMapper<Holiday>() {

			@Override
			public Holiday mapRow(ResultSet rs, int rowNum) throws SQLException {
				Holiday holiday = new Holiday();
				holiday.setHoliday_date(rs.getDate("holiday_date"));
				holiday.setHoliday_name(rs.getString("holiday_name"));
				return holiday;
			}

		});
	}

	public void addHoliday(Date date, String name) {
		this.jdbcTemplate.update("INSERT IGNORE INTO SoldDate (sold_date) VALUES (?);", date);
		this.jdbcTemplate.update("INSERT INTO Holiday (holiday_date, holiday_name) VALUES (?,?)", date, name);
	}

	public void deleteHoliday(String deleteHolidayDate) {
		this.jdbcTemplate.update("DELETE FROM Holiday WHERE holiday_date = ?", deleteHolidayDate);
	}
}
