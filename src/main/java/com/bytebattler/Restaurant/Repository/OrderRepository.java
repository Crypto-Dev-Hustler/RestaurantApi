package com.bytebattler.Restaurant.Repository;

import com.bytebattler.Restaurant.Models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
