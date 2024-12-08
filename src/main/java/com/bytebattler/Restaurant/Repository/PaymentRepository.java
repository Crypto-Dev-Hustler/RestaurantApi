package com.bytebattler.Restaurant.Repository;


import com.bytebattler.Restaurant.Models.PaymentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentModel, String> {
}
