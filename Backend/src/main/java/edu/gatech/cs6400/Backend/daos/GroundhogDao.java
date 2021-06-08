package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import edu.gatech.cs6400.Backend.beans.Groundhog;

@Repository
public class GroundhogDao {
	@Autowired
	JdbcTemplate jdbcGroundhog;

	public List<Groundhog> getGroundhog() {
		String sql = "SELECT \r\n" + 
				"sold_year, \r\n" + 
				"total_num, \r\n" + 
				"avg_num, \r\n" + 
				"groundhog_num \r\n" + 
				"FROM \r\n" + 
				"    (SELECT \r\n" + 
				"	YEAR(T.sold_date) AS sold_year, \r\n" + 
				"	SUM(T.quantity) AS total_num, \r\n" + 
				"	ROUND(SUM(T.quantity)/365, 2) AS avg_num \r\n" + 
				"    FROM Product AS P, \r\n" + 
				"	Category AS C, \r\n" + 
				"	ProductTransaction AS T \r\n" + 
				"    WHERE P.PID = C.PID AND T.PID = C.PID AND C.category = 'Air Conditioner' \r\n" + 
				"    GROUP BY sold_year) AS Y \r\n" + 
				"    NATURAL JOIN  (SELECT YEAR(T.sold_date) AS sold_year, \r\n" + 
				"	               SUM(T.quantity) AS groundhog_num \r\n" + 
				"                   FROM Product AS P, \r\n" + 
				"				   Category AS C, \r\n" + 
				"				   ProductTransaction AS T\r\n" + 
				"                   WHERE P.PID = C.PID AND T.PID=C.PID AND C.category = 'Air Conditioner' AND month(T.sold_date) = \"02\" and day(T.sold_date) = \"02\"\r\n" + 
				"				   GROUP BY T.sold_date) AS G\r\n" + 
				"                   ORDER by sold_year ASC;";

		return jdbcGroundhog.query(sql, new RowMapper<Groundhog>() {
			@Override
			public Groundhog mapRow(ResultSet rs, int rowNum) throws SQLException {
				Groundhog groundhog = new Groundhog();
				groundhog.setSold_year(rs.getInt("sold_year"));
				groundhog.setTotal_num(rs.getInt("total_num"));
				groundhog.setAvg_num(rs.getDouble("avg_num"));
				groundhog.setGroundhog_num(rs.getInt("groundhog_num"));
				return groundhog;

			}
		});
	}

}
