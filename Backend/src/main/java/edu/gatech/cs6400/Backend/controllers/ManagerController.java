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

import edu.gatech.cs6400.Backend.beans.Manager;
import edu.gatech.cs6400.Backend.beans.ActiveManager;
import edu.gatech.cs6400.Backend.services.ManagerService;

@RestController // annotation in Spring used to indicate this class is a controller for handling
				// http requests
@RequestMapping("/managers")
@CrossOrigin(origins = "*")
// https://spring.io/guides/tutorials/rest/
public class ManagerController {
	@Autowired
	private ManagerService ms;

	@GetMapping
	public List<Manager> getManagers() {
		return ms.getManagers();
	}

	@PostMapping
	public void addManager(@RequestBody Manager newManager) {
		ms.addManager(newManager.getEmail(), newManager.getManager_name());
	}

	@DeleteMapping("/delete/{email}")
	public void deleteManager(@PathVariable String email) {
		ms.deleteManager(email);
	}

	@GetMapping("/activeManager/{email}")
	public List<ActiveManager> getManagingStore(@PathVariable String email) {
		return ms.getManagingStore(email);
	}

	@DeleteMapping("/delete/email/{email}/storeID/{storeID}")
	public void unassignStore(@PathVariable String email, @PathVariable int storeID) {
		ms.unassignStore(email, storeID);
	}

	// time format "2008-02-25T05:00:00.000Z"
	@PostMapping("/activeManager")
	public void assignStore(@RequestBody ActiveManager am) {
		ms.assignStore(am);
	}

}
