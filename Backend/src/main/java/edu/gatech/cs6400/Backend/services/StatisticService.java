package edu.gatech.cs6400.Backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gatech.cs6400.Backend.beans.DateRange;
import edu.gatech.cs6400.Backend.beans.State;
import edu.gatech.cs6400.Backend.beans.StoreID;
import edu.gatech.cs6400.Backend.daos.StatisticDao;

@Service
public class StatisticService {
	@Autowired
	private StatisticDao sd;

	public List<State> getStates() {
		return sd.getStates();
	}
	
	public List<StoreID> getStores() {
		return sd.getStores();
	}
	public List<DateRange> getDateRange() {
		return sd.getDateRange();
	}
}
