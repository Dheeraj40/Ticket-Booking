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
import com.test.ticketGeneration.enumeration.RedeemCause;
import com.test.ticketGeneration.repository.StationLkpRepo;
import com.test.ticketGeneration.repository.TktMetaInfoRepo;
import com.test.ticketGeneration.req.BuyTicketRequest;
import com.test.ticketGeneration.res.CreatedTicketResponse;
import com.test.ticketGeneration.res.PriceDto;
import com.test.ticketGeneration.res.RecoveredTicket;
import com.test.ticketGeneration.res.StationInformation;

import jakarta.transaction.Transactional;

@Service
public class ServiceForTicket {

	@Autowired
	StationLkpRepo stationLkpRepo;
	@Autowired
	TktMetaInfoRepo tktMetaInfoRepo;

	public StationInformation getAllStationsDetail() {
		List<StationLkp> stations = stationLkpRepo.findAllByOrderByStationIdAsc();

		Map<String, PriceDto> stationToPriceMap = new HashMap<>();
		stations.forEach(station -> {
			PriceDto price = PriceDto.builder().price(station.getPrice())
					.startStation(!station.getFirstStation() ? null : station.getFirstStation())
					.lastStation(!station.getLastStation() ? null : station.getLastStation()).build();
			stationToPriceMap.put(station.getStationName(), price);
		});
		return StationInformation.builder().stations(stationToPriceMap).build();

	}
	@Transactional
	public CreatedTicketResponse createTicket(BuyTicketRequest tktMetaData) {
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
		return CreatedTicketResponse.builder().ticketId(ticket.getTktId()).validatedTill(ticket.getValidatedTo()).build();

	}
	@Transactional
	public RecoveredTicket redeem(Long tktId) {
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
					return RecoveredTicket.builder().isRedemable(true).reedemed(redeem).build();
				} else if (metaInfo.getReedemCount()+1 <= 2) {
					metaInfo.setStatus("I");
					metaInfo = tktMetaInfoRepo.save(metaInfo);
					return RecoveredTicket.builder().isRedemable(false).cause(RedeemCause.REDEMED_TIME_EXCEED.getCause()).build();
				} else {
					metaInfo.setStatus("I");
					metaInfo = tktMetaInfoRepo.save(metaInfo);
					return RecoveredTicket.builder().isRedemable(false).cause(RedeemCause.REDEMED_TWICE.getCause()).build();
				}
			case "I":
				if(metaInfo.getReedemCount()+1 <= 2) {
					return RecoveredTicket.builder().isRedemable(false).cause(RedeemCause.REDEMED_TIME_EXCEED.getCause()).build();
				} else {
					return RecoveredTicket.builder().isRedemable(false).cause(RedeemCause.REDEMED_TWICE.getCause()).build();
				}
			default:
				throw new IllegalStateException("unkown status");
			}
		} else {
			return RecoveredTicket.builder().isRedemable(false).cause(RedeemCause.INVALID_ID.getCause()).build();
		}
		
	}

}
