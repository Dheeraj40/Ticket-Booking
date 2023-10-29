package com.test.ticketGeneration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.test.ticketGeneration.repository.StationLkpRepo;


@SpringBootApplication
public class TicketGenerator {

	@Autowired
	StationLkpRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(TicketGenerator.class, args);
	}



}
