package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.ActVsPreRev;

@Repository
public class ActVsPreRevDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<ActVsPreRev> getActVsPreRev() {
		String sql = "SELECT PID,product_name,price,total_quantity,sale_quantity, non_sale_quantity, actural_revenue,predicted_revenue,(actural_revenue-predicted_revenue) AS difference FROM \n" + 
				"(SELECT e.*, product_name, price,(sale_quantity*0.75 + non_sale_quantity)*price AS predicted_revenue, (sale_revenue+non_sale_quantity*price) AS actural_revenue\n" + 
				"FROM (SELECT TotalSold.*, sale_quantity,(total_quantity-sale_quantity)AS non_sale_quantity,sale_revenue From \n" + 
				"(SELECT PID, sum(quantity) AS total_quantity FROM\n" + 
				"(SELECT pt.* FROM\n" + 
				"Category INNER JOIN ProductTransaction pt ON  Category.PID=pt.PID\n" + 
				"WHERE Category.category=\"GPS\") GPS \n" + 
				"GROUP BY PID) TotalSold    left JOIN\n" + 
				"(SELECT PID,price,sum(quantity) AS sale_quantity,sum(quantity*sold_price) AS sale_revenue\n" + 
				"FROM (SELECT a.*, pt.quantity FROM\n" + 
				"(SELECT ps.*, price FROM\n" + 
				"(SELECT Category.PID,price\n" + 
				"FROM Category INNER JOIN Product ON Category.PID=Product.PID\n" + 
				" WHERE Category.category=\"GPS\") rprice\n" + 
				" JOIN ProductSold ps ON ps.PID=rprice.PID and ps.sold_price<rprice.price) a\n" + 
				" INNER JOIN    (SELECT sold_date, PID, sum(quantity) AS quantity\n" + 
				" From ProductTransaction GROUP BY PID, sold_date) pt\n" + 
				" ON pt.PID=a.PID AND pt.sold_date=a.sold_date) Summary   GROUP BY PID,price ) ns\n" + 
				" ON TotalSold.PID=ns.PID) e  INNER JOIN Product On Product.PID=e.PID) f\n" + 
				" WHERE ABS(actural_revenue-predicted_revenue)>=10 ORDER BY difference DESC;";
		return this.jdbcTemplate.query(sql, new RowMapper<ActVsPreRev>() {
			@Override
			public ActVsPreRev mapRow(ResultSet rs, int rowNum) throws SQLException {
				ActVsPreRev avp = new ActVsPreRev();
				avp.setPID(rs.getInt("PID"));
				avp.setProduct_name(rs.getString("product_name"));
				avp.setPrice(rs.getDouble("price"));
				avp.setTotal_quantity(rs.getInt("total_quantity"));
				avp.setNon_sale_quantity(rs.getInt("non_sale_quantity"));
				avp.setSale_quantity(rs.getInt("sale_quantity"));
				avp.setPredicted_revenue(rs.getDouble("predicted_revenue"));
				avp.setActural_revenue(rs.getDouble("actural_revenue"));
				avp.setDifference(rs.getDouble("difference"));
				return avp;
			}
		});
	}
}
