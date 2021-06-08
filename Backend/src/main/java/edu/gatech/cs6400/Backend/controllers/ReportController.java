package edu.gatech.cs6400.Backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import edu.gatech.cs6400.Backend.services.ReportService;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "*")
public class ReportController {

	@Autowired
	ReportService reportService;

	@GetMapping("/revenue-by-population/{populationSize}")
	public List<StoreTotalRevenueByPopulation> getRevenueByPopulation(@PathVariable String populationSize) {
		switch (populationSize.toLowerCase()) {
		case "small":
			return reportService.getRevenueByPopulation(0, 3700000);
		case "medium":
			return reportService.getRevenueByPopulation(3700000, 6700000);
		case "large":
			return reportService.getRevenueByPopulation(6700000, 9000000);
		case "extralarge":
			return reportService.getRevenueByPopulation(9000000, Integer.MAX_VALUE);
		}
		return null;
	}

	@GetMapping("/revenue-by-population")
	public List<CityYearlyRevenueByPopulationSize> getRevenueByPopulation() {
		return reportService.getRevenueByPopulation();
	}

	@GetMapping("/actual-vs-predicted-revenue")
	public List<ActVsPreRev> getAVPR() {
		return reportService.getAVPS();
	}

	@GetMapping("/storeyearlyrev/{state_name}")
	public List<StoreYearlyRev> getStoreYearlyRev(@PathVariable String state_name) {
		return reportService.getStoreYearlyRev(state_name);
	}

	@GetMapping("/manufacturers")
	public List<Manufacturer> getManufacturers() {
		return reportService.getManufacturers();
	}

	@GetMapping("/manufacturers/{manufacturerName}")
	public ManufacturerDetail getManufacturerDetail(@PathVariable String manufacturerName) {
		return reportService.getManufacturerDetail(manufacturerName);
	}

	@GetMapping("/categories")
	public List<Category> getCategoryReport() {
		return reportService.getCategoryReport();
	}

	@GetMapping("/ac-on-groundhog-day")
	public List<Groundhog> getGroundhogDayReport() {
		return reportService.getGroundHogDayReport();
	}

	@GetMapping("/dashboard")
	public List<Dashboard> getDashboardReport() {
		return reportService.getDashboardReport();
	}

	@GetMapping("/stateVolume/{year_chosen}/{month_chosen}")
	public List<StateVolume> getStateVolume(@PathVariable String year_chosen, @PathVariable String month_chosen) {
		return reportService.getStateVolume(year_chosen, month_chosen);
	}

	@GetMapping("/stateVolume/{state_chosen}/{year_chosen}/{month_chosen}/{category}")
	public List<StateVolumeDetail> getStateVolumeDetail(@PathVariable String state_chosen,@PathVariable String year_chosen,@PathVariable String month_chosen,@PathVariable String category) {
		return reportService.getStateVolumeDetail(state_chosen,year_chosen,month_chosen,category);
	}
}
