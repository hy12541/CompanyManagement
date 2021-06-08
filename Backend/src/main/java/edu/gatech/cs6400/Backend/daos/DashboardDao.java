package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.Dashboard;


@Repository
public class DashboardDao {
		@Autowired
		JdbcTemplate jdbcDashboard;
		
		public List<Dashboard> getDashboard() {
		String sql = "SELECT " 
				  + "(SELECT COUNT(DISTINCT email) FROM Manager) AS count_manager, "
				  + "(SELECT COUNT(DISTINCT manufacturer) FROM Manufacturer) AS count_manufacturer, "
				  + "(SELECT COUNT(DISTINCT PID) FROM Product) AS count_product, "
				  + "(SELECT COUNT(DISTINCT storeID) FROM Store) AS count_store ";


		/*Dashboard d = new Dashboard();
	
		jdbcDashboard.query(sql, (ResultSet rs) -> {
			
			d.setCount_manager(rs.getInt("count_manager"));
			d.setCount_manufacturer(rs.getInt("count_manufacturer"));
			d.setCount_store(rs.getInt("count_product"));
			d.setCount_product(rs.getInt("count_store"));
		}
	);
		return d;
		
*/		
		
	
		return jdbcDashboard.query(sql, new RowMapper<Dashboard>() {
			@Override
			public Dashboard mapRow(ResultSet rs, int rowNum) throws SQLException {
				Dashboard d = new Dashboard();
				d.setCount_manager(rs.getInt("count_manager"));
				d.setCount_manufacturer(rs.getInt("count_manufacturer"));
				d.setCount_product(rs.getInt("count_product"));
				d.setCount_store(rs.getInt("count_store"));
				return d;

			}
		});	
		
		}
}

