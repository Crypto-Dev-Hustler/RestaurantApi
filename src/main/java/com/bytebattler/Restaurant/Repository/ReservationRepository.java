package com.bytebattler.Restaurant.Repository;

import com.bytebattler.Restaurant.Models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
}
