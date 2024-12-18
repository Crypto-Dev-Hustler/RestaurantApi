package com.bytebattler.Restaurant.Models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "MenuItems")
@NoArgsConstructor
public class MenuModel {
	@Id
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

	@Override
	public String toString() {
		return String.format("Item Name: %s\nDescription: %s\nPrice: %.2f\nAvailable: %s\nImage URL: %s\n", itemName, description, price, isAvailable ? "Yes" : "No", imageUrl);
	}
}
