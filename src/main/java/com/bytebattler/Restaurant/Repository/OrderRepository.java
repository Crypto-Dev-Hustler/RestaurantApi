package com.bytebattler.Restaurant.Repository;

import com.bytebattler.Restaurant.Models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
