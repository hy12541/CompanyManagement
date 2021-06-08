package edu.gatech.cs6400.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // annotation used by Spring to indicate this class is the overall configuration
						// class and entry point
public class BackendApplication {
	// method below: entry point of the backend java server application. Will start
	// the server application by excecute this method.
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
