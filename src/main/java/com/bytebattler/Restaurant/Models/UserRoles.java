package com.bytebattler.Restaurant.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "UserRole")
@Getter
@Setter
public class UserRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long role_id;

	@NonNull
	@Column(unique = true)
	private String roleName;

	@ManyToMany(mappedBy = "roles")
	private Set<UserModel> users = new HashSet<>();

}
