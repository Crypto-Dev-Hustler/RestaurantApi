package com.bytebattler.Restaurant.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ReviewDTO {
	private String id;

	@NonNull
	private String userId;

	@NonNull
	private String itemId;

	@NonNull
	private String reviewText;

	private Integer rating;
}


