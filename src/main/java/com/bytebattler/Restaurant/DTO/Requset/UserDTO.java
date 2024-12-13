package com.bytebattler.Restaurant.DTO.Requset;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {

	@NonNull
	private String userName;

	@NonNull
	private String userEmail;

	@NonNull
	private String password;

	@NonNull
	private String phoneNumber;

	private Set<String> roles;
}


