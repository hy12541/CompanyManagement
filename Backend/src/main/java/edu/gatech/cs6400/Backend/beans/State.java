package edu.gatech.cs6400.Backend.beans;
import lombok.Data;

@Data
public class State {

	private String state_name;

	public State(String state_name) {
		super();
		this.state_name = state_name;
	}
	public State() { 
		super();
	}
}
