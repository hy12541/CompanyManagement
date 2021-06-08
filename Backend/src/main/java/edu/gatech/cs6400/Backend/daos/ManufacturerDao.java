package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.Manufacturer;
import edu.gatech.cs6400.Backend.beans.ManufacturerDetail;
import edu.gatech.cs6400.Backend.beans.ManufacturerProduct;

@Repository
public class ManufacturerDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Manufacturer> getManufacturer() {

		String sql = "SELECT manufacturer, COUNT(DISTINCT PID) AS total_number, ROUND(AVG(price), 2) AS avg_price, MIN(price) AS min_price, MAX(price) AS max_price"
				+ " FROM Product" + " GROUP BY manufacturer " + " ORDER BY avg_price DESC LIMIT 100;";

		return jdbcTemplate.query(sql, new RowMapper<Manufacturer>() {
			@Override
			public Manufacturer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setManufacturer(rs.getString("manufacturer"));
				manufacturer.setTotal_number(rs.getInt("total_number"));
				manufacturer.setAvg_price(rs.getDouble("avg_price"));
				manufacturer.setMin_price(rs.getDouble("min_price"));
				manufacturer.setMax_price(rs.getDouble("max_price"));
				return manufacturer;
			}
		});

	}

	public ManufacturerDetail getManufacturerDetail(String name) {
		ManufacturerDetail manufacturerDetail = new ManufacturerDetail();
		manufacturerDetail.setManufacturerProducts(new ArrayList<ManufacturerProduct>());

		String queryForNameAndMaxDiscount = "SELECT manufacturer, max_discount FROM " + "Manufacturer WHERE "
				+ "Manufacturer.manufacturer = ? ";
		String queryForProducts = "SELECT PID, product_name, price FROM Product  "
				+ "WHERE manufacturer= ? ORDER BY price DESC";
		String queryForCategories = "SELECT category FROM Category " + "WHERE PID = ? ;";
		jdbcTemplate.query(queryForNameAndMaxDiscount, (ResultSet rs) -> {
			manufacturerDetail.setManufacturerName(rs.getString("manufacturer"));
			manufacturerDetail.setMaxDiscount(rs.getDouble("max_discount"));
		}, name);
		jdbcTemplate.query(queryForProducts, (ResultSet rs) -> {
			ManufacturerProduct manufacturerProduct = new ManufacturerProduct();
			manufacturerProduct.setProductPID(rs.getInt("PID"));
			manufacturerProduct.setProductName(rs.getString("product_name"));
			manufacturerProduct.setProductPrice(rs.getDouble("price"));
			manufacturerProduct.setProductCategories(
					Arrays.toString(jdbcTemplate.queryForList(queryForCategories, String.class, rs.getInt("PID")).toArray()));
			manufacturerDetail.getManufacturerProducts().add(manufacturerProduct);
		}, name);
		return manufacturerDetail;
	}

}
