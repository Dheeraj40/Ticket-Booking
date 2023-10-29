package com.test.ticketGeneration.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.ticketGeneration.entities.StationLkp;
import com.test.ticketGeneration.entities.TicketMetaInfo;
import com.test.ticketGeneration.enums.RedeemCause;
import com.test.ticketGeneration.repository.StationLkpRepo;
import com.test.ticketGeneration.repository.TktMetaInfoRepo;
import com.test.ticketGeneration.request.BuyTicketRequest;
import com.test.ticketGeneration.response.GeneratedTicketResponse;
import com.test.ticketGeneration.response.PriceDto;
import com.test.ticketGeneration.response.RedemedTicket;
import com.test.ticketGeneration.response.StationDetail;

import jakarta.transaction.Transactional;

@Service
public class TicketService {

	@Autowired
	StationLkpRepo stationLkpRepo;
	@Autowired
	TktMetaInfoRepo tktMetaInfoRepo;

	public StationDetail getAllStationsDetail() {
		List<StationLkp> stations = stationLkpRepo.findAllByOrderByStationIdAsc();

		Map<String, PriceDto> stationToPriceMap = new HashMap<>();
		stations.forEach(station -> {
			PriceDto price = PriceDto.builder().price(station.getPrice())
					.startStation(!station.getFirstStation() ? null : station.getFirstStation())
					.lastStation(!station.getLastStation() ? null : station.getLastStation()).build();
			stationToPriceMap.put(station.getStationName(), price);
		});
		return StationDetail.builder().stations(stationToPriceMap).build();

	}
	@Transactional
	public GeneratedTicketResponse createTicket(BuyTicketRequest tktMetaData) {
		Date curDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.HOUR_OF_DAY, 18);
		Date validUpto = cal.getTime();

		StationLkp stationStart = stationLkpRepo.findByStationName(tktMetaData.getFromStation()).orElseThrow();
		StationLkp stationEnd = stationLkpRepo.findByStationName(tktMetaData.getToStation()).orElseThrow();

		TicketMetaInfo ticket = TicketMetaInfo.builder().createDate(curDate).endStation(stationEnd)
				.price(tktMetaData.getPrice()).startStation(stationStart).reedemCount(0).status("A").validatedTo(validUpto).build();

		ticket = tktMetaInfoRepo.save(ticket);
		return GeneratedTicketResponse.builder().ticketId(ticket.getTktId()).validatedTill(ticket.getValidatedTo()).build();

	}
	@Transactional
	public RedemedTicket redeem(Long tktId) {
		TicketMetaInfo metaInfo = tktMetaInfoRepo.findByTktId(tktId).orElse(null);
		if(Objects.nonNull(metaInfo)) {
			switch(metaInfo.getStatus()) {
			case "A":
				Date curDate = new Date();
				Date validUpto = metaInfo.getValidatedTo();
				if(curDate.before(validUpto) && metaInfo.getReedemCount()+1 <= 2) {
					metaInfo.setReedemCount(metaInfo.getReedemCount()+1);
					metaInfo = tktMetaInfoRepo.save(metaInfo);
					String redeem = "Congratulations!! Ticket ID: "+tktId+" has been redeemed. Happy Journey!! ";
					return RedemedTicket.builder().isRedemable(true).reedemed(redeem).build();
				} else if (metaInfo.getReedemCount()+1 <= 2) {
					metaInfo.setStatus("I");
					metaInfo = tktMetaInfoRepo.save(metaInfo);
					return RedemedTicket.builder().isRedemable(false).cause(RedeemCause.REDEMED_TIME_EXCEED.getCause()).build();
				} else {
					metaInfo.setStatus("I");
					metaInfo = tktMetaInfoRepo.save(metaInfo);
					return RedemedTicket.builder().isRedemable(false).cause(RedeemCause.REDEMED_TWICE.getCause()).build();
				}
			case "I":
				if(metaInfo.getReedemCount()+1 <= 2) {
					return RedemedTicket.builder().isRedemable(false).cause(RedeemCause.REDEMED_TIME_EXCEED.getCause()).build();
				} else {
					return RedemedTicket.builder().isRedemable(false).cause(RedeemCause.REDEMED_TWICE.getCause()).build();
				}
			default:
				throw new IllegalStateException("unkown status");
			}
		} else {
			return RedemedTicket.builder().isRedemable(false).cause(RedeemCause.INVALID_ID.getCause()).build();
		}
		
	}

}
