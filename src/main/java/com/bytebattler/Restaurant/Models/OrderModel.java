package com.bytebattler.Restaurant.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "OrderDetail")
public class OrderModel {
	public OrderModel() {
		// TODO This is  a Default constructor
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_id;

	@NonNull
	private String status;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "itemIds", joinColumns = @JoinColumn(name = "order_id"))
	@Column(name = "menu_item_id")
	private List<String> items = new ArrayList<>();

	@Version
	private int version;

	@Override
	public String toString() {
		return "{ Order_id= %d, Status= %s, Item= %s, }".formatted(order_id, status, items);

	}
}