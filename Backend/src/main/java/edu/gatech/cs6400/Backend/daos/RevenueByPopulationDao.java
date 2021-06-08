package edu.gatech.cs6400.Backend.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.gatech.cs6400.Backend.beans.CityYearlyRevenueByPopulationSize;
import edu.gatech.cs6400.Backend.beans.StoreTotalRevenueByPopulation;

@Repository
public class RevenueByPopulationDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<StoreTotalRevenueByPopulation> getRenueByPopulation(int i, int j) {
		return this.jdbcTemplate.query("SELECT SUM(quantity*sold_price) as revenue, storeID "
				+ "FROM ProductTransaction LEFT JOIN ProductSold ON " + "ProductTransaction.PID = ProductSold.PID "
				+ "AND ProductTransaction.sold_date = ProductSold.sold_date " + "WHERE "
				+ "storeID IN (SELECT storeID FROM Store where (city, state_name) IN "
				+ "(SELECT city, state_name FROM City where population BETWEEN ? and ?)) " + " GROUP BY storeID;",
				(rs, rowNum) -> {
					StoreTotalRevenueByPopulation revenueByPopulation = new StoreTotalRevenueByPopulation();
					revenueByPopulation.setRevenue(rs.getDouble("revenue"));
					revenueByPopulation.setStoreID(rs.getInt("storeID"));
					return revenueByPopulation;
				}, i, j);
	}

	public List<CityYearlyRevenueByPopulationSize> getRenueByPopulation() {
		String sql = "select distinct \r\n" + 
				"revenue_t4.report_year as year\r\n" + 
				",floor(revenue_t4_1.revenue/1000) as smallCity\r\n" + 
				",floor(revenue_t4_2.revenue/1000) as mediumCity\r\n" + 
				",floor(revenue_t4_3.revenue/1000) as largeCity\r\n" + 
				",floor(revenue_t4_4.revenue/1000) as extraLargeCity\r\n" + 
				"from (select \r\n" + 
				"      revenue_t3.city_size\r\n" + 
				"      ,revenue_t3.report_year\r\n" + 
				"      ,avg(revenue_t3.revenue) as revenue\r\n" + 
				"      from (select \r\n" + 
				"            City.state_name\r\n" + 
				"            ,City.city\r\n" + 
				"			,City.population\r\n" + 
				"            ,case when City.population< 3700000 then \"Small\"\r\n" + 
				"                  when City.population >= 3700000 and City.population < 6700000 then \"Medium\"\r\n" + 
				"                  when City.population >= 6700000 and City.population < 9000000 then \"Large\"\r\n" + 
				"                  when City.population >= 9000000 then \"Extra Large\" end as city_size\r\n" + 
				"            ,revenue_t2.report_year\r\n" + 
				"            ,revenue_t2.revenue\r\n" + 
				"            from City\r\n" + 
				"            left join (select revenue_t1.state_name\r\n" + 
				"                      ,revenue_t1.city\r\n" + 
				"                      ,year(revenue_t1.sold_date) as report_year\r\n" + 
				"                      ,sum(revenue_t1.revenue) as revenue\r\n" + 
				"                      from (select \r\n" + 
				"                            ProductTransaction.storeID\r\n" + 
				"                           ,Store.state_name\r\n" + 
				"                           ,Store.city\r\n" + 
				"                           ,ProductTransaction.PID\r\n" + 
				"                           ,ProductTransaction.sold_date\r\n" + 
				"                           ,case when ProductSold.sold_price is not null then ProductTransaction.quantity * ProductSold.sold_price \r\n" + 
				"						   else ProductTransaction.quantity * Product.price end as revenue\r\n" + 
				"                           from ProductTransaction\r\n" + 
				"                           left join ProductSold on ProductTransaction.PID = ProductSold.PID and ProductTransaction.sold_date = ProductSold.sold_date\r\n" + 
				"						   left join Product on ProductTransaction.PID = Product.PID\r\n" + 
				"                           left join Store on ProductTransaction.storeID = Store.storeID  ) as revenue_t1\r\n" + 
				"                      group by revenue_t1.state_name\r\n" + 
				"                      ,revenue_t1.city\r\n" + 
				"                      ,year(revenue_t1.sold_date)\r\n" + 
				"					  ) as revenue_t2 on City.state_name = revenue_t2.state_name and City.city = revenue_t2.city\r\n" + 
				"					  ) as revenue_t3\r\n" + 
				"      group by revenue_t3.city_size\r\n" + 
				"      ,revenue_t3.report_year\r\n" + 
				"	  ) as revenue_t4\r\n" + 
				"left join (select report_year, revenue \r\n" + 
				"           from (select \r\n" + 
				"                 revenue_t3.city_size\r\n" + 
				"                 ,revenue_t3.report_year\r\n" + 
				"                 ,avg(revenue_t3.revenue) as revenue\r\n" + 
				"                 from (select \r\n" + 
				"                       City.state_name\r\n" + 
				"                       ,City.city\r\n" + 
				"                       ,case when City.population< 3700000 then \"Small\"\r\n" + 
				"                             when City.population >= 3700000 and City.population < 6700000 then \"Medium\"\r\n" + 
				"                             when City.population >= 6700000 and City.population < 9000000 then \"Large\"\r\n" + 
				"                             when City.population >= 9000000 then \"Extra Large\" end as city_size\r\n" + 
				"                       ,revenue_t2.report_year\r\n" + 
				"                       ,revenue_t2.revenue\r\n" + 
				"                       from City\r\n" + 
				"                       left join (select revenue_t1.state_name\r\n" + 
				"                                 ,revenue_t1.city\r\n" + 
				"                                 ,year(revenue_t1.sold_date) as report_year\r\n" + 
				"                                 ,sum(revenue_t1.revenue) as revenue\r\n" + 
				"                                 from (select \r\n" + 
				"                                       ProductTransaction.storeID\r\n" + 
				"                                      ,Store.state_name\r\n" + 
				"                                      ,Store.city\r\n" + 
				"                                      ,ProductTransaction.PID\r\n" + 
				"                                      ,ProductTransaction.sold_date\r\n" + 
				"                                      ,case when ProductSold.sold_price is not null then ProductTransaction.quantity * ProductSold.sold_price \r\n" + 
				"						              else ProductTransaction.quantity * Product.price end as revenue\r\n" + 
				"                                      from ProductTransaction\r\n" + 
				"                                      left join ProductSold on ProductTransaction.PID = ProductSold.PID and ProductTransaction.sold_date = ProductSold.sold_date\r\n" + 
				"						              left join Product on ProductTransaction.PID = Product.PID\r\n" + 
				"                                      left join Store on ProductTransaction.storeID = Store.storeID  ) as revenue_t1\r\n" + 
				"                                 group by revenue_t1.state_name\r\n" + 
				"                                 ,revenue_t1.city\r\n" + 
				"                                 ,year(revenue_t1.sold_date)) as revenue_t2 on City.state_name = revenue_t2.state_name and City.city = revenue_t2.city) as revenue_t3\r\n" + 
				"                 group by revenue_t3.city_size\r\n" + 
				"                 ,revenue_t3.report_year) as revenue_t4\r\n" + 
				"		    where city_size = \"Small\") as revenue_t4_1 on revenue_t4.report_year = revenue_t4_1.report_year\r\n" + 
				"left join (select report_year, revenue \r\n" + 
				"           from (select \r\n" + 
				"                 revenue_t3.city_size\r\n" + 
				"                 ,revenue_t3.report_year\r\n" + 
				"                 ,avg(revenue_t3.revenue) as revenue\r\n" + 
				"                 from (select \r\n" + 
				"                       City.state_name\r\n" + 
				"                       ,City.city\r\n" + 
				"                       ,case when City.population< 3700000 then \"Small\"\r\n" + 
				"                             when City.population >= 3700000 and City.population < 6700000 then \"Medium\"\r\n" + 
				"                             when City.population >= 6700000 and City.population < 9000000 then \"Large\"\r\n" + 
				"                             when City.population >= 9000000 then \"Extra Large\" end as city_size\r\n" + 
				"                       ,revenue_t2.report_year\r\n" + 
				"                       ,revenue_t2.revenue\r\n" + 
				"                       from City\r\n" + 
				"                       left join (select revenue_t1.state_name\r\n" + 
				"                                 ,revenue_t1.city\r\n" + 
				"                                 ,year(revenue_t1.sold_date) as report_year\r\n" + 
				"                                 ,sum(revenue_t1.revenue) as revenue\r\n" + 
				"                                 from (select \r\n" + 
				"                                       ProductTransaction.storeID\r\n" + 
				"                                      ,Store.state_name\r\n" + 
				"                                      ,Store.city\r\n" + 
				"                                      ,ProductTransaction.PID\r\n" + 
				"                                      ,ProductTransaction.sold_date\r\n" + 
				"                                      ,case when ProductSold.sold_price is not null then ProductTransaction.quantity * ProductSold.sold_price \r\n" + 
				"						              else ProductTransaction.quantity * Product.price end as revenue\r\n" + 
				"                                      from ProductTransaction\r\n" + 
				"                                      left join ProductSold on ProductTransaction.PID = ProductSold.PID and ProductTransaction.sold_date = ProductSold.sold_date\r\n" + 
				"						              left join Product on ProductTransaction.PID = Product.PID\r\n" + 
				"                                      left join Store on ProductTransaction.storeID = Store.storeID  ) as revenue_t1\r\n" + 
				"                                 group by revenue_t1.state_name\r\n" + 
				"                                 ,revenue_t1.city\r\n" + 
				"                                 ,year(revenue_t1.sold_date)) as revenue_t2 on City.state_name = revenue_t2.state_name and City.city = revenue_t2.city) as revenue_t3\r\n" + 
				"                 group by revenue_t3.city_size\r\n" + 
				"                 ,revenue_t3.report_year) as revenue_t4\r\n" + 
				"		    where city_size = \"Medium\") as revenue_t4_2 on revenue_t4.report_year = revenue_t4_2.report_year\r\n" + 
				"left join (select report_year, revenue \r\n" + 
				"           from (select \r\n" + 
				"                 revenue_t3.city_size\r\n" + 
				"                 ,revenue_t3.report_year\r\n" + 
				"                 ,avg(revenue_t3.revenue) as revenue\r\n" + 
				"                 from (select \r\n" + 
				"                       City.state_name\r\n" + 
				"                       ,City.city\r\n" + 
				"                       ,case when City.population< 3700000 then \"Small\"\r\n" + 
				"                             when City.population >= 3700000 and City.population < 6700000 then \"Medium\"\r\n" + 
				"                             when City.population >= 6700000 and City.population < 9000000 then \"Large\"\r\n" + 
				"                             when City.population >= 9000000 then \"Extra Large\" end as city_size\r\n" + 
				"                       ,revenue_t2.report_year\r\n" + 
				"                       ,revenue_t2.revenue\r\n" + 
				"                       from City\r\n" + 
				"                       left join (select revenue_t1.state_name\r\n" + 
				"                                 ,revenue_t1.city\r\n" + 
				"                                 ,year(revenue_t1.sold_date) as report_year\r\n" + 
				"                                 ,sum(revenue_t1.revenue) as revenue\r\n" + 
				"                                 from (select \r\n" + 
				"                                       ProductTransaction.storeID\r\n" + 
				"                                      ,Store.state_name\r\n" + 
				"                                      ,Store.city\r\n" + 
				"                                      ,ProductTransaction.PID\r\n" + 
				"                                      ,ProductTransaction.sold_date\r\n" + 
				"                                      ,case when ProductSold.sold_price is not null then ProductTransaction.quantity * ProductSold.sold_price \r\n" + 
				"						              else ProductTransaction.quantity * Product.price end as revenue\r\n" + 
				"                                      from ProductTransaction\r\n" + 
				"                                      left join ProductSold on ProductTransaction.PID = ProductSold.PID and ProductTransaction.sold_date = ProductSold.sold_date\r\n" + 
				"						              left join Product on ProductTransaction.PID = Product.PID\r\n" + 
				"                                      left join Store on ProductTransaction.storeID = Store.storeID  ) as revenue_t1\r\n" + 
				"                                 group by revenue_t1.state_name\r\n" + 
				"                                 ,revenue_t1.city\r\n" + 
				"                                 ,year(revenue_t1.sold_date)) as revenue_t2 on City.state_name = revenue_t2.state_name and City.city = revenue_t2.city) as revenue_t3\r\n" + 
				"                 group by revenue_t3.city_size\r\n" + 
				"                 ,revenue_t3.report_year) as revenue_t4\r\n" + 
				"		    where city_size = \"Large\") as revenue_t4_3 on revenue_t4.report_year = revenue_t4_3.report_year\r\n" + 
				"left join (select report_year, revenue \r\n" + 
				"           from (select \r\n" + 
				"                 revenue_t3.city_size\r\n" + 
				"                 ,revenue_t3.report_year\r\n" + 
				"                 ,avg(revenue) as revenue\r\n" + 
				"                 from (select \r\n" + 
				"                       City.state_name\r\n" + 
				"                       ,City.city\r\n" + 
				"                       ,case when City.population< 3700000 then \"Small\"\r\n" + 
				"                             when City.population >= 3700000 and City.population < 6700000 then \"Medium\"\r\n" + 
				"                             when City.population >= 6700000 and City.population < 9000000 then \"Large\"\r\n" + 
				"                             when City.population >= 9000000 then \"Extra Large\" end as city_size\r\n" + 
				"                       ,revenue_t2.report_year\r\n" + 
				"                       ,revenue_t2.revenue\r\n" + 
				"                       from City\r\n" + 
				"                       left join (select revenue_t1.state_name\r\n" + 
				"                                 ,revenue_t1.city\r\n" + 
				"                                 ,year(revenue_t1.sold_date) as report_year\r\n" + 
				"                                 ,sum(revenue_t1.revenue) as revenue\r\n" + 
				"                                 from (select \r\n" + 
				"                                       ProductTransaction.storeID\r\n" + 
				"                                      ,Store.state_name\r\n" + 
				"                                      ,Store.city\r\n" + 
				"                                      ,ProductTransaction.PID\r\n" + 
				"                                      ,ProductTransaction.sold_date\r\n" + 
				"                                      ,case when ProductSold.sold_price is not null then ProductTransaction.quantity * ProductSold.sold_price \r\n" + 
				"						              else ProductTransaction.quantity * Product.price end as revenue\r\n" + 
				"                                      from ProductTransaction\r\n" + 
				"                                      left join ProductSold on ProductTransaction.PID = ProductSold.PID and ProductTransaction.sold_date = ProductSold.sold_date\r\n" + 
				"						              left join Product on ProductTransaction.PID = Product.PID\r\n" + 
				"                                      left join Store on ProductTransaction.storeID = Store.storeID  ) as revenue_t1\r\n" + 
				"                                 group by revenue_t1.state_name\r\n" + 
				"                                 ,revenue_t1.city\r\n" + 
				"                                 ,year(revenue_t1.sold_date)) as revenue_t2 on City.state_name = revenue_t2.state_name and City.city = revenue_t2.city) as revenue_t3\r\n" + 
				"                 group by revenue_t3.city_size\r\n" + 
				"                 ,revenue_t3.report_year) as revenue_t4\r\n" + 
				"		    where city_size = \"Extra Large\") as revenue_t4_4 on revenue_t4.report_year = revenue_t4_4.report_year\r\n" + 
				"order by revenue_t4.report_year;";
		return this.jdbcTemplate.query(sql, (rs, rowNum) -> {
				CityYearlyRevenueByPopulationSize row = new CityYearlyRevenueByPopulationSize();
				row.setYear(rs.getInt("year"));
				row.setSmallCity(rs.getDouble("smallCity"));
				row.setMediumCity(rs.getDouble("mediumCity"));
				row.setLargeCity(rs.getDouble("largeCity"));
				row.setExtraLargeCity(rs.getDouble("extraLargeCity"));
				return row;
			});
	}

}
