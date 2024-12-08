package com.bytebattler.Restaurant.Services;


import com.bytebattler.Restaurant.Models.PaymentModel;
import com.bytebattler.Restaurant.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Transactional
	public PaymentModel processPayment(PaymentModel payment) {
		payment.setStatus("success");
		payment.setTransactionId(UUID.randomUUID().toString());
		payment.setMessage("Payment processed successfully.");
		return paymentRepository.save(payment);
	}

	@Transactional(readOnly = true)
	public Optional<PaymentModel> getPaymentById(String id) {
		return paymentRepository.findById(id);
	}
}
