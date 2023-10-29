package com.test.ticketGeneration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.test.ticketGeneration.entities.StationLkp;
import com.test.ticketGeneration.repository.StationLkpRepo;

import jakarta.transaction.Transactional;


@SpringBootApplication
public class TicketGenerationApplication {

	@Autowired
	StationLkpRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(TicketGenerationApplication.class, args);
	}



}
