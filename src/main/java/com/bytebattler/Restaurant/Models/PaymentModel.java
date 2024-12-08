package com.bytebattler.Restaurant.Models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "payments")
public class PaymentModel {
	@Id
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
