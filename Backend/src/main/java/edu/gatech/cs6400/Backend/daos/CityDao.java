package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.City;

@Repository // Annotation used by spring to register this class as a DAO layer
public class CityDao {

	@Autowired // Spring will automatically inject a JdbcTemplate object. This object has
				// predefined method to execute SQL.
	JdbcTemplate jdbctemplate;

	// method below: get all cities from the city table in database and map the
	// result to List<city>
	public List<City> getCities() {
		return this.jdbctemplate.query("SELECT * FROM City", new RowMapper<City>() {
			@Override
			public City mapRow(ResultSet rs, int rowNum) {
				City city = new City();
				try {
					city.setState_name(rs.getString("state_name"));
					city.setCity(rs.getString("city"));
					city.setPopulation(rs.getInt("population"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return city;
			}
		});

	}

	public List<City> getPopulation(String state_name,String city) {
		return this.jdbctemplate.query("SELECT * FROM City WHERE state_name=? AND city=? ", new RowMapper<City>() {
			@Override
			public City mapRow(ResultSet rs, int rowNum) throws SQLException {
				City c = new City();
				c.setState_name(rs.getString("state_name"));
				c.setCity(rs.getString("city"));
				c.setPopulation(rs.getInt("population"));
				return c;
			}
		}, state_name,city);
	}
	
	public void updateCity(City newCity, City oldCity) {

		this.jdbctemplate.update(
				"UPDATE City SET city = ?, state_name = ?, population = ? WHERE city = ? AND state_name = ? AND population = ?",
				newCity.getCity(), newCity.getState_name(), newCity.getPopulation(), oldCity.getCity(),
				oldCity.getState_name(), oldCity.getPopulation());
	}

}
//public void addCity(int population, String state_name, String city) {
		// the code below: a complicated version
		// this.jdbctemplate.update("INSERT INTO City (population, state_name, city)
		// VALUES (?, ?, ?)", new PreparedStatementSetter() {
		//
		// @Override
		// public void setValues(PreparedStatement ps) {
		// try {
		// ps.setInt(1, population);
		// ps.setString(2, state_name);
		// ps.setString(3, city);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// });
		// code below: a short version with the same result
		//this.jdbctemplate.update("INSERT INTO City (population, state_name, city) VALUES (?, ?, ?)", population,
				//state_name, city);
		// code below: another way
		// Object[] parameters= {population, state_name, city};
		// int[] types= {4, 12, 12};
		// this.jdbctemplate.update("INSERT INTO City (population, state_name, city)
		// VALUES (?, ?, ?)", parameters, types);
	//}

	/*
	 * public void deleteCity(int population, String state, String name) {
	 * 
	 * this.jdbctemplate.
	 * update("DELETE FROM City WHERE population = ? AND state_name = ? AND city = ?"
	 * , new PreparedStatementSetter() {
	 * 
	 * @Override public void setValues(PreparedStatement ps) { try { ps.setInt(1,
	 * population); ps.setString(2, state); ps.setString(3, name); } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * }
	 * 
	 * }); }
	 */