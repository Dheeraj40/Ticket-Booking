package com.test.ticketGeneration.res;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatedTicketResponse implements Serializable{

	
	private static final long serialVersionUID = 1864531324354L;
	private static final String currentTimeZone = "Asia/Calcutta";
	Long ticketId;
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = currentTimeZone)
	Date validatedTill;
	

	
}
