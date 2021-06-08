package edu.gatech.cs6400.Backend.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import edu.gatech.cs6400.Backend.beans.StateVolume;
import edu.gatech.cs6400.Backend.beans.StateVolumeDetail;

@Repository
public class StateVolumeDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<StateVolume> getStateVolume(String year_chosen, String month_chosen) {
		
		String sql = "SELECT \r\n" +
				"category_transactions_state_summ_max.category\r\n" + 
				",category_transactions_state_summ.state_name\r\n" + 
				",category_transactions_state_summ_max.max_units_sold\r\n" + 
				"FROM \r\n" + 
				"    (SELECT \r\n" + 
				"    category_transactions_state_summ.category\r\n" + 
				"    ,MAX(category_transactions_state_summ.units_sold) AS max_units_sold\r\n" + 
				"    FROM \r\n" + 
				"        (SELECT \r\n" + 
				"        a2.category\r\n" + 
				"        ,a2.state_name\r\n" + 
				"        ,SUM(a2.quantity) AS units_sold\r\n" + 
				"        FROM \r\n" + 
				"            (SELECT a1.*\r\n" + 
				"            ,b1.state_name\r\n" + 
				"            FROM \r\n" + 
				"                (SELECT a.category\r\n" + 
				"                ,a.PID\r\n" + 
				"                ,b.storeID\r\n" + 
				"                ,b.sold_date\r\n" + 
				"                ,b.quantity\r\n" + 
				"                FROM Category a\r\n" + 
				"                LEFT JOIN ProductTransaction b ON a.PID = b.PID\r\n" + 
				"                WHERE YEAR(b.sold_date) = ? AND MONTH(b.sold_date) = ? ) AS a1\r\n" + 
				"            LEFT JOIN Store b1 ON a1.storeID = b1.storeID) AS a2\r\n" + 
				"        GROUP BY \r\n" + 
				"        a2.category\r\n" + 
				"        ,a2.state_name) AS category_transactions_state_summ\r\n" + 
				"    GROUP BY category_transactions_state_summ.category) AS category_transactions_state_summ_max\r\n" + 
				"LEFT JOIN \r\n" + 
				"    (SELECT \r\n" + 
				"    a2.category\r\n" + 
				"       ,a2.state_name\r\n" + 
				"       ,SUM(a2.quantity) AS units_sold\r\n" + 
				"       FROM \r\n" + 
				"           (SELECT a1.*\r\n" + 
				"           ,b1.state_name\r\n" + 
				"           FROM \r\n" + 
				"               (SELECT a.category\r\n" + 
				"               ,a.PID\r\n" + 
				"               ,b.storeID\r\n" + 
				"               ,b.sold_date\r\n" + 
				"               ,b.quantity\r\n" + 
				"               FROM Category a\r\n" + 
				"               LEFT JOIN ProductTransaction b ON a.PID = b.PID\r\n" + 
				"               WHERE YEAR(b.sold_date) = ? AND MONTH(b.sold_date) = ? ) AS a1\r\n" + 
				"           LEFT JOIN Store b1 ON a1.storeID = b1.storeID) AS a2\r\n" + 
				"       GROUP BY \r\n" + 
				"     a2.category\r\n" + 
				"    ,a2.state_name) AS category_transactions_state_summ\r\n" + 
				"ON category_transactions_state_summ_max.max_units_sold = category_transactions_state_summ.units_sold \r\n" + 
				"AND category_transactions_state_summ_max.category = category_transactions_state_summ.category\r\n" + 
				"ORDER BY category_transactions_state_summ_max.category\r\n" + 
				";";
		return this.jdbcTemplate.query(sql, new RowMapper<StateVolume>() {
			@Override
			public StateVolume mapRow(ResultSet rs, int rowNum) throws SQLException {
				StateVolume sv = new StateVolume();
				sv.setCategory(rs.getString("category"));
				sv.setState_name(rs.getString("state_name"));
				sv.setMax_units_sold(rs.getInt("max_units_sold"));
				return sv;
			}
		},year_chosen,month_chosen,year_chosen,month_chosen);
	}
	public List<StateVolumeDetail> getStateVolumeDetail(String state_chosen, String year_chosen, String month_chosen, String category) {
		String sql_detail = "select \r\n" + 
				" a1.storeID     \r\n" + 
				",a1.city     \r\n" + 
				",a1.address     \r\n" + 
				",b1.manager_name    \r\n" + 
				",a1.email\r\n" + 
				"from  \r\n" + 
				"    (select distinct\r\n" + 
				"    a.storeID\r\n" + 
				"    ,c.city\r\n" + 
				"    ,c.address\r\n" + 
				"    ,d.email\r\n" + 
				"    ,year(a.sold_date) as report_year\r\n" + 
				"    ,month(a.sold_date) as report_month\r\n" + 
				"    \r\n" + 
				"    ,b.category\r\n" + 
				"    ,c.state_name\r\n" + 
				"    from ProductTransaction as a\r\n" + 
				"    left join Category b on a.PID = b.PID\r\n" + 
				"    left join Store c on a.storeID = c.storeID\r\n" + 
				"    left join ActiveManager d on a.storeID = d.storeID\r\n" + 
				"    where year(a.sold_date) = ? and month(a.sold_date) = ?\r\n" + 
				"    and c.state_name = ?\r\n" + 
				"    and b.category = ?\r\n" + 
				"    and d.email is not null) a1\r\n" + 
				"left join Manager b1 ON a1.email = b1.email \r\n" + 
				"order by  a1.storeID  \r\n" + 
				";";
		return this.jdbcTemplate.query(sql_detail, new RowMapper<StateVolumeDetail>() {
			@Override
			public StateVolumeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				StateVolumeDetail sv_detail = new StateVolumeDetail();
				sv_detail.setCity(rs.getString("city"));
				sv_detail.setAddress(rs.getString("address"));
				sv_detail.setStoreID(rs.getInt("storeID"));
				sv_detail.setManager_name(rs.getString("manager_name"));
				sv_detail.setEmail(rs.getString("email"));
				return sv_detail;
			}
		},year_chosen,month_chosen,state_chosen,category);
	}

}