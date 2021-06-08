package edu.gatech.cs6400.Backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gatech.cs6400.Backend.beans.City;
import edu.gatech.cs6400.Backend.daos.CityDao;

@Service // Annotation used by spring to register this class as a service layer class
public class CityService {

	@Autowired
	private CityDao cd;

	public List<City> getCities() {
		return cd.getCities();
	}

	public List<City> getPopulation(String state_name,String city) {
		return cd.getPopulation(state_name,city);
	}

	public void updateCity(City newCity, City oldCity) {
		cd.updateCity(newCity, oldCity);
	}

	
}
/*
 * public void addCity(City newCity) { cd.addCity(newCity.getPopulation(),
 * newCity.getState_name(), newCity.getCity()); } public void deleteCity(City
 * cityToBeDeleted) { cd.deleteCity(cityToBeDeleted.getPopulation(),
 * cityToBeDeleted.getState_name(), cityToBeDeleted.getCity()); }
 */
