package com.bytebattler.Restaurant.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@Document(collection = "reviews")
public class ReviewModel {
	@Id
	private String id;

	@NonNull
	private String userId;

	@NonNull
	private String itemId;

	@NonNull
	private String reviewText;

	private Integer rating;
}
