package com.bytebattler.Restaurant.Repository;

import com.bytebattler.Restaurant.Models.ReviewModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<ReviewModel, String> {
}
