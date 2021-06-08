package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.Product;

@Repository
public class ProductDao {
	@Autowired
	JdbcTemplate jdbctemplate;

	public List<Product> getProduct() {
		return this.jdbctemplate.query("SELECT * FROM Product", new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) {
				Product product = new Product();
				try {
					product.setPID(rs.getInt("PID"));
					product.setProduct_name(rs.getString("product_name"));
					product.setPrice(rs.getDouble("price"));
					product.setManufacturer(rs.getString("manufacturer"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				return product;
			}
		});

	}
}
