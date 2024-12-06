package com.bytebattler.Restaurant.Models;

import lombok.Data;


@Data
public class MenuModel {
	private String id;
	private String itemName;
	private String description;
	private double price;
	private String imageUrl;
}
