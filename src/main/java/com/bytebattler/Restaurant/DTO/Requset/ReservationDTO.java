package com.bytebattler.Restaurant.DTO.Requset;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ReservationDTO {
	private Long id;

	@NonNull
	private String date;

	@NonNull
	private String time;

	@NonNull
	private Integer numberOfPeople;

	@NonNull
	private String contactName;

	@NonNull
	private String contactPhone;

	@NonNull
	private String status;
}


