package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.StoreYearlyRev;

@Repository
public class StoreYearlyRevDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<StoreYearlyRev> getStoreYearlyRev(String state_name) {
		String sql = "Select sold_year,g.StoreID, address, city,  (non_sale_revenue+on_sale_revenue)as total_revenue FROM\n" + 
				"(select f.storeID, f.sold_year,coalesce(non_sale_revenue,0) as non_sale_revenue,coalesce(on_sale_revenue,0) as on_sale_revenue from\n" + 
				"(select sold_year, storeID, sum(price*non_sale_quantity) as non_sale_revenue from\n" + 
				"     (select nst.*, price from\n" + 
				"     (select year(sold_date) as sold_year, storeID, PID, sum(quantity) as non_sale_quantity from\n" + 
				"			(SELECT sold_date,PID,storeID,quantity\n" + 
				"            From (select h.*,i.sold_price from ProductSold i right join \n" + 
				"            (SELECT pt.* FROM  ProductTransaction pt Join\n" + 
				"	 Store s ON s.storeID=pt.storeID \n" + 
				"	 WHERE state_name=?) h\n" + 
				"            on i.sold_date=h.sold_date and i.PID=h.PID) j where sold_price is null) k\n" + 
				"     group by PID, year(sold_date), storeID)nst\n" + 
				"     join Product on Product.PID=nst.PID) c\n" + 
				"  group by sold_year, storeID) e\n" + 
				"right join (SELECT storeID,YEAR(sold_date) as sold_year, sum(revenue) AS on_sale_revenue FROM  \n" + 
				"(SELECT a.sold_date, a.quantity*b.sold_price as revenue, a.storeID  \n" + 
				"FROM (SELECT pt.* FROM  ProductTransaction pt Join\n" + 
				"	 Store s ON s.storeID=pt.storeID \n" + 
				"	 WHERE state_name=?) a  \n" + 
				"left JOIN ProductSold b On a.PID= b.PID And a.sold_date=b.sold_date) Dailysold \n" + 
				"GROUP BY storeID, year(sold_date)) f \n" + 
				"on e.storeID=f.storeID and e.sold_year=f.sold_year) g\n" + 
				"JOIN Store ON Store.StoreID=g.StoreID\n" + 
				" ORDER BY sold_year ASC,total_revenue DESC;\n";
				
				/*
				
				"Select s.StoreID, s.address, s.city, sold_year, revenue AS total_revenue FROM "
				+ "(SELECT storeID,YEAR(sold_date) as sold_year, sum(daily_revenue) AS revenue FROM "
				+ "(SELECT a.sold_date, a.quantity*b.sold_price as daily_revenue, a.storeID "
				+ "FROM ProductTransaction a "
				+ "INNER JOIN ProductSold b On a.PID= b.PID And a.sold_date=b.sold_date) Dailysold "
				+ "GROUP BY storeID, YEAR(sold_date)) YearSold " + "RIGHT JOIN Store s ON s.StoreID=YearSold.StoreID  "
				+ "WHERE state_name=? " + "ORDER BY sold_year ASC,total_revenue DESC;";
				
				
				*/
		return this.jdbcTemplate.query(sql, new RowMapper<StoreYearlyRev>() {
			@Override
			public StoreYearlyRev mapRow(ResultSet rs, int rowNum) throws SQLException {
				StoreYearlyRev syr = new StoreYearlyRev();
				syr.setAddress(rs.getString("address"));
				syr.setCity(rs.getString("city"));
				syr.setTotal_revenue(rs.getDouble("total_revenue"));
				syr.setStoreID(rs.getInt("storeID"));
				syr.setSold_year(rs.getInt("sold_year"));
				return syr;
			}
		}, state_name,state_name);
	}
}
