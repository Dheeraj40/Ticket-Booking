package com.test.ticketGeneration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.ticketGeneration.request.BuyTicketRequest;
import com.test.ticketGeneration.response.GeneratedTicketResponse;
import com.test.ticketGeneration.response.RedemedTicket;
import com.test.ticketGeneration.response.StationDetail;
import com.test.ticketGeneration.service.TicketService;

@RestController
public class TicketController {

	@Autowired
	TicketService ticketService;

	/**
	 * @return All the station with price detail
	 */
	@GetMapping("/stationList")
	public @ResponseBody StationDetail stationList() {
		return ticketService.getAllStationsDetail();
	}

	@PostMapping("/generateTicket")
	public @ResponseBody GeneratedTicketResponse generateTicket(@RequestBody BuyTicketRequest request) {
		
		System.out.println(request);
//		return GeneratedTicketResponse.builder().ticketId(1L).validatedTill(new Date()).build();
		 return ticketService.createTicket(request);
	}
	
	@PostMapping("/redemTicket")
	public @ResponseBody RedemedTicket redemTicket(@RequestParam("ticketId") Long ticketId) {
		return ticketService.redeem(ticketId);
	}

}
