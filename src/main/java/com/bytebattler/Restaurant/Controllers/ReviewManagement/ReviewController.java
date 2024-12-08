package com.bytebattler.Restaurant.Controllers.ReviewManagement;

import com.bytebattler.Restaurant.Models.ReviewModel;
import com.bytebattler.Restaurant.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping
	public ResponseEntity<ReviewModel> addReview(@RequestBody ReviewModel review) {
		ReviewModel createdReview = reviewService.addReview(review);
		return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReviewModel> getReviewById(@PathVariable String id) {
		Optional<ReviewModel> review = reviewService.getReviewById(id);
		return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReviewModel> updateReview(@PathVariable String id, @RequestBody ReviewModel updatedReview) {
		ReviewModel review = reviewService.updateReview(id, updatedReview);
		if (review != null) {
			return new ResponseEntity<>(review, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReview(@PathVariable String id) {
		boolean isDeleted = reviewService.deleteReview(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
