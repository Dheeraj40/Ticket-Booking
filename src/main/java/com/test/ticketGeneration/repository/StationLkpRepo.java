package com.test.ticketGeneration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.ticketGeneration.entities.StationLkp;

public interface StationLkpRepo extends JpaRepository<StationLkp, Long> {
	
	// select col from StationLkp order by stationid asc 
	List<StationLkp> findAllByOrderByStationIdAsc();
	
	// select col from StationLkp where StationName = stationName
	Optional<StationLkp> findByStationName(String stationName);
}
