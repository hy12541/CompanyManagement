package edu.gatech.cs6400.Backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gatech.cs6400.Backend.beans.Manager;
import edu.gatech.cs6400.Backend.beans.ActiveManager;
import edu.gatech.cs6400.Backend.daos.ManagerDao;

@Service
public class ManagerService {
	@Autowired
	private ManagerDao md;

	public List<Manager> getManagers() {
		return md.getManagers();
	}

	public void addManager(String email, String manager_name) {
		md.addManager(email, manager_name);
	}

	public void deleteManager(String email) {
		md.deleteManager(email);
	}

	public List<ActiveManager> getManagingStore(String email) {
		return md.getManagingStore(email);
	}

	public void assignStore(ActiveManager am) {
		md.assignStore(am.getEmail(), am.getStoreID());
	}

	public void unassignStore(String email, int storeID) {
		md.unassignStore(email, storeID);
		
	}

}
