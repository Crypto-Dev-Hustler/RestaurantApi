package com.bytebattler.Restaurant.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Users")
public class UserModel {

	public UserModel() {
		// TODO This is  a Default constructor
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;

	@NonNull
	@Column(unique = true)
	private String userName;

	@NonNull
	private String userEmail;

	@NonNull
	private String password;

	@NonNull
	private String phoneNumber;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "junction", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<UserRoles> roles = new HashSet<>();

}
