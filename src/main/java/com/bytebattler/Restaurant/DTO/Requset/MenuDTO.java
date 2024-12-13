package com.bytebattler.Restaurant.DTO.Requset;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MenuDTO {
	private String id;

	@NonNull
	private String itemName;

	@NonNull
	private String description;

	@NonNull
	private Double price;

	@NonNull
	private Boolean isAvailable;

	private String imageUrl;
}


 