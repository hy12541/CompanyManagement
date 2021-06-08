package edu.gatech.cs6400.Backend.beans;
import lombok.Data;

@Data
public class StoreID {
	private int storeID;
	
	public StoreID(int storeID) {
			super();
			this.storeID = storeID;
		}
		public StoreID() { 
			super();
		}
}
