package com.bytebattler.Restaurant.Services;

import com.bytebattler.Restaurant.Models.ReviewModel;
import com.bytebattler.Restaurant.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;

	@Transactional
	public ReviewModel addReview(ReviewModel review) {
		return reviewRepository.save(review);
	}

	@Transactional(readOnly = true)
	public Optional<ReviewModel> getReviewById(String id) {
		return reviewRepository.findById(id);
	}

	@Transactional
	public ReviewModel updateReview(String id, ReviewModel updatedReview) {
		Optional<ReviewModel> existingReviewOptional = reviewRepository.findById(id);
		if (existingReviewOptional.isPresent()) {
			ReviewModel existingReview = existingReviewOptional.get();
			if (updatedReview.getReviewText() != null && !updatedReview.getReviewText().isEmpty()) {
				existingReview.setReviewText(updatedReview.getReviewText());
			}
			if (updatedReview.getRating() != null) {
				existingReview.setRating(updatedReview.getRating());
			}
			return reviewRepository.save(existingReview);
		} else {
			return null;
		}
	}

	@Transactional
	public boolean deleteReview(String id) {
		if (reviewRepository.existsById(id)) {
			reviewRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
