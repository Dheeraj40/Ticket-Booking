package com.test.ticketGeneration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.ticketGeneration.req.BuyTicketRequest;
import com.test.ticketGeneration.res.CreatedTicketResponse;
import com.test.ticketGeneration.res.RecoveredTicket;
import com.test.ticketGeneration.res.StationInformation;
import com.test.ticketGeneration.service.ServiceForTicket;

@RestController
public class TicketController {

	@Autowired
	ServiceForTicket ticketService;

	/**
	 * @return All the station with price detail
	 */
	@GetMapping("/stationList")
	public @ResponseBody StationInformation stationList() {
		return ticketService.getAllStationsDetail();
	}

	@PostMapping("/generateTicket")
	public @ResponseBody CreatedTicketResponse generateTicket(@RequestBody BuyTicketRequest request) {
		
		System.out.println(request);
//		return GeneratedTicketResponse.builder().ticketId(1L).validatedTill(new Date()).build();
		 return ticketService.createTicket(request);
	}
	
	@PostMapping("/redemTicket")
	public @ResponseBody RecoveredTicket redemTicket(@RequestParam("ticketId") Long ticketId) {
		return ticketService.redeem(ticketId);
	}

}
