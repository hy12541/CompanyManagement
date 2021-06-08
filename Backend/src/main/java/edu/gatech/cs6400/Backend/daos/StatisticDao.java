package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.DateRange;
import edu.gatech.cs6400.Backend.beans.State;
import edu.gatech.cs6400.Backend.beans.StoreID;

@Repository
public class StatisticDao {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<State> getStates() {
		return this.jdbctemplate.query("SELECT DISTINCT state_name FROM City ORDER BY state_name", new RowMapper<State>() {
			@Override
			public State mapRow(ResultSet rs, int rowNum) {
				State s = new State();
				try {
					s.setState_name(rs.getString("state_name"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return s;
			}
		});

	}
	
	public List<StoreID> getStores() {
		return this.jdbctemplate.query("SELECT storeID FROM Store ORDER BY storeID", new RowMapper<StoreID>() {
			@Override
			public StoreID mapRow(ResultSet rs, int rowNum) {
				StoreID si = new StoreID();
				try {
					si.setStoreID(rs.getInt("storeID"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return si;
			}
		});
		}
		public List<DateRange> getDateRange() {
			return this.jdbctemplate.query("select min(sold_date) as min_date,max(sold_date) as max_date from SoldDate where sold_date<>\"0000-00-00\";", new RowMapper<DateRange>() {
				@Override
				public DateRange mapRow(ResultSet rs, int rowNum) {
					DateRange dr = new DateRange();
					try {
						dr.setMin_date(rs.getDate("min_date"));
						dr.setMax_date(rs.getDate("max_date"));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return dr;
				}
			});
	   }
	}
