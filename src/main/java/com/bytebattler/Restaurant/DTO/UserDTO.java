package com.bytebattler.Restaurant.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {
	private Long userId;

	@NonNull
	private String userName;

	@NonNull
	private String userEmail;

	@NonNull
	private String phoneNumber;

	private Set<String> roles;
}


