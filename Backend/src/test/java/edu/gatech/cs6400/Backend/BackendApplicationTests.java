package edu.gatech.cs6400.Backend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gatech.cs6400.Backend.controllers.CityController;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BackendApplicationTests {
	
	@Autowired
	private CityController cc;

	@Test
	public void contextLoads() throws Exception {
		assertThat(cc).isNotNull();
	}

}

