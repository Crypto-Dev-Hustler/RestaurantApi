package com.bytebattler.Restaurant.DTO.Requset;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class PaymentDTO {
	private String id;

	@NonNull
	private Double amount;

	@NonNull
	private String currency;

	@NonNull
	private String paymentMethod;

	@NonNull
	private String status;

	private String transactionId;

	private String message;
}



