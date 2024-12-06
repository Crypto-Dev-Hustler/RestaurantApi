package com.bytebattler.Restaurant.Models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collation = "MenuItems")
public class MenuModel {
	@Id
	private String id;

	@NonNull
	private String itemName;

	@NonNull
	private String description;

	@NonNull
	private double price;

	@NonNull
	private boolean isAvailable;
	private String imageUrl;
}
