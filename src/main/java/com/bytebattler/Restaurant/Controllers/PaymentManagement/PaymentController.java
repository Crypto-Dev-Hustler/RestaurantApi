package com.bytebattler.Restaurant.Controllers.PaymentManagement;

import com.bytebattler.Restaurant.Models.PaymentModel;
import com.bytebattler.Restaurant.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping()
	public ResponseEntity<PaymentModel> processPayment(@RequestBody PaymentModel payment) {
		PaymentModel processedPayment = paymentService.processPayment(payment);
		return new ResponseEntity<>(processedPayment, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentModel> getPaymentById(@PathVariable String id) {
		Optional<PaymentModel> payment = paymentService.getPaymentById(id);
		return payment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
