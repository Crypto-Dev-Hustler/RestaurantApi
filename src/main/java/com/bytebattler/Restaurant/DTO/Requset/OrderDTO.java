package com.bytebattler.Restaurant.DTO.Requset;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {
	private Long orderId;

	@NonNull
	private String status;

	@NonNull
	private List<String> items;
}


