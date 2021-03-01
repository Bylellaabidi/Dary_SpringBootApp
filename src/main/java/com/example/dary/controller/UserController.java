package com.example.dary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dary.model.User;
import com.example.dary.repository.UserRepository;
import com.example.dary.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService ; 
	@Autowired
	private UserRepository userRepository ; 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(method=RequestMethod.GET, value="/users" )
	public List<User> getAll(){
		return userService.getAll();
	}
}
