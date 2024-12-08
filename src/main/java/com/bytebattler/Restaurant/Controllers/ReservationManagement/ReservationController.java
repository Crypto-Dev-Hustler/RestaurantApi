package com.bytebattler.Restaurant.Controllers.ReservationManagement;

import com.bytebattler.Restaurant.Models.ReservationModel;
import com.bytebattler.Restaurant.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping("/create")
	public ResponseEntity<?> createReservation(@RequestBody ReservationModel reservation) {
		reservation.setStatus("pending");
		boolean createdReservation = reservationService.createReservation(reservation);
		return createdReservation ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}

	@PatchMapping("/updateStatus/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
		reservationService.changeStatus(id, status);
		return ResponseEntity.status(HttpStatus.OK).build();
	}


	@GetMapping("/get")
	public ResponseEntity<List<ReservationModel>> getAllReservations() {
		List<ReservationModel> reservations = reservationService.getAllReservations();
		if (reservations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ReservationModel> getReservationById(@PathVariable Long id) {
		Optional<ReservationModel> reservation = reservationService.getReservationById(id);
		return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ReservationModel> updateReservationStatus(@PathVariable Long id, @RequestBody ReservationModel reservation) {
		boolean isUpdated = reservationService.updateReservationStatus(id, reservation);
		if (isUpdated) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
		boolean isDeleted = reservationService.deleteReservation(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
