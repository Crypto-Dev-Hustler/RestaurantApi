package com.bytebattler.Restaurant.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Reservations")
public class ReservationModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
