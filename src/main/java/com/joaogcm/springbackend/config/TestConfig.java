package com.joaogcm.springbackend.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.joaogcm.springbackend.services.DatabaseService;
import com.joaogcm.springbackend.services.EmailService;
import com.joaogcm.springbackend.services.MockEmailService;

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
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}