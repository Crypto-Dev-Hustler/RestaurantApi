package com.bytebattler.Restaurant.Services;

import com.bytebattler.Restaurant.Models.ReservationModel;
import com.bytebattler.Restaurant.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;

	@Transactional
	public boolean createReservation(ReservationModel reservation) {

		try {
			reservation.setStatus("pending"); // Initial status as pending
			reservationRepository.save(reservation);
			return true;
		} catch (DataAccessException e) {
			System.err.println("Failed to create reservation: " + e.getMessage());
			return false;
		}
	}

	@Transactional(readOnly = true)
	public Optional<ReservationModel> getReservationById(Long id) {
		return reservationRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<ReservationModel> getAllReservations() {
		return reservationRepository.findAll();
	}


	@Transactional
	public boolean updateReservationStatus(Long id, ReservationModel res) {
		Optional<ReservationModel> existingReservationOptional = reservationRepository.findById(id);
		if (existingReservationOptional.isPresent()) {
			ReservationModel existingReservation = existingReservationOptional.get();

			if (res.getDate() != null && !res.getDate().isEmpty()) {
				existingReservation.setDate(res.getDate());
			}
			if (res.getTime() != null && !res.getTime().isEmpty()) {
				existingReservation.setTime(res.getTime());
			}
			if (res.getNumberOfPeople() != null && res.getNumberOfPeople() >= 1) {
				existingReservation.setNumberOfPeople(res.getNumberOfPeople());
			}
			if (res.getContactPhone() != null && !res.getContactPhone().isEmpty()) {
				existingReservation.setContactPhone(res.getContactPhone());
			}
			if (res.getContactName() != null && !res.getContactName().isEmpty()) {
				existingReservation.setContactName(res.getContactName());
			}
			if (res.getStatus() != null && (res.getStatus().equals("pending") || res.getStatus().equals("confirmed"))) {
				existingReservation.setStatus(res.getStatus());
			}

			reservationRepository.save(existingReservation);
			return true;
		} else {
			return false;
		}
	}


	@Transactional
	public void changeStatus(Long id, String status) {
		Optional<ReservationModel> res = reservationRepository.findById(id);
		if (res.isPresent()) {
			ReservationModel resStatus = res.get();
			if (status.equals("pending") || status.equals("confirmed")) {
				resStatus.setStatus(status);
				reservationRepository.save(resStatus);
				System.out.println("Reservation status updated to " + status + " for ID: " + id);
			} else {
				System.out.println("Invalid status: " + status);
			}
		} else {
			System.out.println("Reservation not found for ID: " + id);
		}
	}


	@Transactional
	public boolean deleteReservation(Long id) {
		if (reservationRepository.existsById(id)) {
			reservationRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
