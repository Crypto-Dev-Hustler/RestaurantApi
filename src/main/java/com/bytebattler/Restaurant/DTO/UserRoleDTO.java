package com.bytebattler.Restaurant.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserRoleDTO {
	private Long roleId;

	@NonNull
	private String roleName;
}