package com.example.dary.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dary.config.JwtTokenUtil;
import com.example.dary.model.CustomUserDetails;
import com.example.dary.model.LoginRequest;
import com.example.dary.model.MessageResponse;
import com.example.dary.model.Role;
import com.example.dary.model.SignupRequest;
import com.example.dary.model.JwtResponse;
import com.example.dary.model.User;
import com.example.dary.repository.RoleRepository;
import com.example.dary.repository.UserRepository;
import com.example.dary.service.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		User user = userRepository.getByUserName(authenticationRequest.getUsername());
		
		return ResponseEntity.ok(new JwtResponse(token,user.getId(),user.getUserName(),user.getEmail(),user.getRoles()));
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) throws Exception {
		if (userRepository.existsByUserName(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		if(!signUpRequest.getPassword().equals(signUpRequest.getPasswordConfirm())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Password!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 passwordEncoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getFirstname(),
							 signUpRequest.getLastname());

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet();

		if (strRoles == null) {
			Role userRole = roleRepository.findByRole("USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByRole("ADMIN")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "user":
					Role modRole = roleRepository.findByRole("USER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByRole("USER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(signUpRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token,user.getId(),user.getUserName(),user.getEmail(),user.getRoles()));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
