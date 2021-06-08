package edu.gatech.cs6400.Backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gatech.cs6400.Backend.beans.ActVsPreRev;
import edu.gatech.cs6400.Backend.beans.Category;
import edu.gatech.cs6400.Backend.beans.CityYearlyRevenueByPopulationSize;
import edu.gatech.cs6400.Backend.beans.Dashboard;
import edu.gatech.cs6400.Backend.beans.Groundhog;
import edu.gatech.cs6400.Backend.beans.Manufacturer;
import edu.gatech.cs6400.Backend.beans.ManufacturerDetail;
import edu.gatech.cs6400.Backend.beans.StateVolume;
import edu.gatech.cs6400.Backend.beans.StateVolumeDetail;
import edu.gatech.cs6400.Backend.beans.StoreTotalRevenueByPopulation;
import edu.gatech.cs6400.Backend.beans.StoreYearlyRev;
import edu.gatech.cs6400.Backend.daos.ActVsPreRevDao;
import edu.gatech.cs6400.Backend.daos.CategoryDao;
import edu.gatech.cs6400.Backend.daos.DashboardDao;
import edu.gatech.cs6400.Backend.daos.GroundhogDao;
import edu.gatech.cs6400.Backend.daos.ManufacturerDao;
import edu.gatech.cs6400.Backend.daos.RevenueByPopulationDao;
import edu.gatech.cs6400.Backend.daos.StateVolumeDao;
import edu.gatech.cs6400.Backend.daos.StoreYearlyRevDao;

@Service
public class ReportService {

	@Autowired
	ActVsPreRevDao avpd;
	@Autowired
	StoreYearlyRevDao syr;
	@Autowired
	RevenueByPopulationDao revenueByPopulationDao;
	@Autowired
	ManufacturerDao manufacturerDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	GroundhogDao groundhogDao;
	@Autowired
	DashboardDao dashboardDao;
	@Autowired
	StateVolumeDao stateVolumeDao;

	public List<StateVolume> getStateVolume(String year_chosen, String month_chosen) {
		return this.stateVolumeDao.getStateVolume(year_chosen, month_chosen);
	}

	public List<StateVolumeDetail> getStateVolumeDetail(String state_chosen, String year_chosen, String month_chosen, String category) {
		return this.stateVolumeDao.getStateVolumeDetail(state_chosen,year_chosen,month_chosen,category);
	}

	public List<StoreYearlyRev> getStoreYearlyRev(String state_name) {
		return this.syr.getStoreYearlyRev(state_name);
	}

	public List<ActVsPreRev> getAVPS() {
		return this.avpd.getActVsPreRev();
	}

	public List<StoreTotalRevenueByPopulation> getRevenueByPopulation(int i, int j) {
		return revenueByPopulationDao.getRenueByPopulation(i, j);
	}

	public List<Manufacturer> getManufacturers() {
		return manufacturerDao.getManufacturer();
	}

	public ManufacturerDetail getManufacturerDetail(String manufacturerName) {
		return manufacturerDao.getManufacturerDetail(manufacturerName);
	}

	public List<Category> getCategoryReport() {
		return categoryDao.getCategoryReport();
	}

	public List<Groundhog> getGroundHogDayReport() {
		return groundhogDao.getGroundhog();
	}

	public List<Dashboard> getDashboardReport() {
		return dashboardDao.getDashboard();
	}

	public List<CityYearlyRevenueByPopulationSize> getRevenueByPopulation() {
		return revenueByPopulationDao.getRenueByPopulation();
	}
}
