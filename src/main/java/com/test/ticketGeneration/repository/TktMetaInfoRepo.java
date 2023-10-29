package com.test.ticketGeneration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.ticketGeneration.entities.TicketMetaInfo;

public interface TktMetaInfoRepo  extends JpaRepository<TicketMetaInfo, Long>{
	
	Optional<TicketMetaInfo> findByTktId(Long id);
	
}
