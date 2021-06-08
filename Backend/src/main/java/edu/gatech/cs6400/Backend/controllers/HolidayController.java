package edu.gatech.cs6400.Backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.cs6400.Backend.beans.Holiday;
import edu.gatech.cs6400.Backend.services.HolidayService;

@RestController
@RequestMapping("/holidays")
@CrossOrigin(origins = "*")
public class HolidayController {

	@Autowired
	HolidayService hs;

	@GetMapping
	public List<Holiday> getHolidays() {
		return this.hs.getHolidays();
	}

	@DeleteMapping("/{deleteHolidayDate}")
	public void deleteHoliday(@PathVariable String deleteHolidayDate) {
		
		this.hs.deleteHoliday(deleteHolidayDate);
	}

	@PostMapping
	public void addHoliday(@RequestBody Holiday newHoliday) {
		this.hs.addHoliday(newHoliday);
	}

}
