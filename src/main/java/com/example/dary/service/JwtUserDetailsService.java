package com.example.dary.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dary.model.CustomUserDetails;
import com.example.dary.model.User;
import com.example.dary.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository usersRepository ; 
	
	@Override
	public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUsers = usersRepository.findByUserName(username);
		
		optionalUsers
			.orElseThrow(() -> new UsernameNotFoundException("Username not found")) ; 
		
		return optionalUsers
					.map(CustomUserDetails :: new ).get() ; 
		
//		User user = usersRepository.findByUserName(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//		return CustomUserDetails.build(user);
	}

}
