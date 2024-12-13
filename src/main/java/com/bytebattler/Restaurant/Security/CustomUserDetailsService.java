package com.bytebattler.Restaurant.Security;

import com.bytebattler.Restaurant.Models.UserModel;
import com.bytebattler.Restaurant.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(STR."User Not Found with : \{username}");
		}

		return User.withUsername(user.getUserName())
				.password(user.getPassword()).roles(user.getRoles().toString()).build();
	}
}
