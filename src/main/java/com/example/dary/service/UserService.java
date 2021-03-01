package com.example.dary.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dary.model.Role;
import com.example.dary.model.User;
import com.example.dary.repository.RoleRepository;
import com.example.dary.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository usersRepository ;
	
	@Autowired
	private RoleRepository roleRepository ;
	
	public List<User> addUser(User user, String role) {
		
		Role rol = roleRepository.getByRole(role);
		
		Set<Role> roleList =  new java.util.HashSet() ;
		roleList.add(rol);
		
		User users = new User();
		
		users.setUserName(user.getUserName());
		users.setEmail(user.getEmail());
		users.setFirstName(user.getFirstName());
		users.setLastName(user.getLastName());
		users.setPassword(user.getPassword());
		users.setRoles(roleList);
		
		usersRepository.save(users);
		
		return usersRepository.findAll();
	}

	public List<User> getAll() {

		return usersRepository.findAll();
	}

	public List<User> updateUser( User user, String role) {
		
		Role rol = roleRepository.getByRole(role);
		
		Set<Role> roleList =  new java.util.HashSet() ;
		roleList.add(rol);
		
		user.setRoles(roleList);
		
		usersRepository.save(user) ; 
		
		return usersRepository.findAll();
	}

	public List<User> deleteUser(int userId) {
		usersRepository.delete(usersRepository.findById(userId));
		return usersRepository.findAll();
	} 

}
