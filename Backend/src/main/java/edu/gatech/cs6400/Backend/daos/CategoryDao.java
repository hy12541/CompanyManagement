package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.Category;

@Repository
public class CategoryDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Category> getCategoryReport() {
		String sql = "SELECT DISTINCT a1.category, COUNT(DISTINCT a1.PID) AS num_product, "
				+ "COUNT(DISTINCT a1.manufacturer) AS num_manufacturename, CAST(AVG(a1.price) AS decimal(10,2)) as avg_price "
				+ "FROM (SELECT a.category, a.PID, c.manufacturer, c.price "
				+ "      FROM Category AS a "
				+ "      LEFT JOIN Product AS c ON a.PID = c.PID) AS a1 "
				+ "GROUP BY a1.category "
				+ "ORDER BY a1.category;";
		return this.jdbcTemplate.query(sql, new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category cat = new Category();
				cat.setCategoryName(rs.getString("category"));
				cat.setNum_product(rs.getInt("num_product"));
				cat.setNum_manufacturename(rs.getInt("num_manufacturename"));
				cat.setAvg_price(rs.getDouble("avg_price"));
				return cat;
			}
		});
	}


}
