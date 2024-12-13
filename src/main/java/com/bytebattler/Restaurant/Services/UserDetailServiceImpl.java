//package com.bytebattler.Restaurant.Services;
//
//import com.bytebattler.Restaurant.Models.UserModel;
//import com.bytebattler.Restaurant.Repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class UserDetailServiceImpl implements UserDetailsService {
//
//	private final UserRepository userRepository;
//
//	@Autowired
//	public UserDetailServiceImpl(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		log.debug("This is userDetail implementation is Ok");
//		UserModel user = userRepository.findByUserName(username);
//		if (user != null) {
//			return User.withUsername(user.getUserName())
//					.password(user.getPassword())
//					.roles(user.getRoles().toString()).build();
//		}
//		throw new UsernameNotFoundException(STR."User not found with this Username :\{username}");
//	}
//}
