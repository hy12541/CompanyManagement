package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.Manager;
import edu.gatech.cs6400.Backend.beans.ActiveManager;

@Repository
public class ManagerDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Manager> getManagers() {
		return this.jdbcTemplate.query("SELECT Manager.*,stores from Manager left join (SELECT COUNT(storeID) as stores,email FROM ActiveManager Group by email) a on a.email=Manager.email ;", new RowMapper<Manager>() {
			@Override
			public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
				Manager manager = new Manager();
				manager.setEmail(rs.getString("email"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setStores(rs.getInt("stores"));
				return manager;
			}
		});
	}

	public void addManager(String email, String manager_name) {
		this.jdbcTemplate.update("INSERT INTO Manager VALUES(?,?)", email, manager_name);
	}

	public void deleteManager(String email) {
		this.jdbcTemplate.update("DELETE FROM Manager WHERE email = ? AND email NOT IN (SELECT DISTINCT email FROM ActiveManager)", email);
	}

	public List<ActiveManager> getManagingStore(String email) {
		// Object[] args= {email};
		return this.jdbcTemplate.query("SELECT * FROM ActiveManager WHERE email=? ", new RowMapper<ActiveManager>() {
			// {public void setValues(PreparedStatement ps) throws
			// SQLException {
			// ps.setString(1, email);}}

			@Override
			public ActiveManager mapRow(ResultSet rs, int rowNum) throws SQLException {
				ActiveManager mp = new ActiveManager();
				mp.setEmail(rs.getString("email"));
				mp.setStoreID(rs.getInt("storeID"));
				return mp;
			}
		}, email);
	}

	public void unassignStore(String email, int storeID) {
		this.jdbcTemplate.update("DELETE FROM ActiveManager WHERE email=?  AND storeID=?",email, storeID);
	}

	public void assignStore(String email, int storeID) {
		this.jdbcTemplate.update("INSERT INTO ActiveManager(email,storeID) VALUES(?,?)", email, storeID);
	}

}
