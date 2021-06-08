package edu.gatech.cs6400.Backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.cs6400.Backend.beans.DateRange;
import edu.gatech.cs6400.Backend.beans.State;
import edu.gatech.cs6400.Backend.beans.StoreID;
import edu.gatech.cs6400.Backend.services.StatisticService;

@RestController
@RequestMapping("/statistics")
@CrossOrigin(origins = "*")

public class StatisticController {
	@Autowired
	StatisticService ss;
	
	@GetMapping("/states")
	public List<State> getStates() {
		return ss.getStates();
	}

	@GetMapping("/stores")
	public List<StoreID> getStores() {
		return ss.getStores();
	}
	
	@GetMapping("/dateRange")
	public List<DateRange> getDateRange() {
		return ss.getDateRange();
	}

}
