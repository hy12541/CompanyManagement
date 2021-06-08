package edu.gatech.cs6400.Backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.cs6400.Backend.beans.City;
import edu.gatech.cs6400.Backend.services.CityService;

@RestController // annotation in Spring used to indicate this class is a controller for handling
				// http requests
@RequestMapping("/cities") // all Http requests ends in "/cities" will be handled by this class
@CrossOrigin(origins = "*") // annotation used to enable CORS (cross origin reosurces sharing).
public class CityController {

	@Autowired // annocaton used for spring to automatically instaticate a CityService object
				// and inject here.
	CityService cs;

	@GetMapping // all get method will be handled by this method
	public List<City> getCities() {
		return cs.getCities();
	}

	@PutMapping // all put method will be handled by this method
	public void updateCity(@RequestBody City[] cities) { // the @RequestBody will parse the body contained in the Http
															// request and assign it to the cities
		cs.updateCity(cities[0], cities[1]);
	}

	@GetMapping ("/population/{state_name}/{city}")
	public List<City> getPopulation(@PathVariable String state_name, @PathVariable String city) {
		return cs.getPopulation(state_name,city);
	}
}

	/*
	 * @PostMapping // all post method will be handled by this method public void
	 * addCity(@RequestBody City newCity) { cs.addCity(newCity); }
	 * 
	 * @DeleteMapping // all delete method will be handled by this method public
	 * void deleteCity(@RequestBody City cityToBeDeleted) {
	 * cs.deleteCity(cityToBeDeleted); }
	 */