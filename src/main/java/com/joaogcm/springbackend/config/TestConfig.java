package com.joaogcm.springbackend.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.joaogcm.springbackend.services.DatabaseService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DatabaseService databaseService;
	
	@Bean
	public Boolean instantiateDatabase() throws ParseException {
		databaseService.instantiateDatabase();
		return true;
	}
}