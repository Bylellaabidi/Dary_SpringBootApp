package com.example.dary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dary.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByUserName(String userName);
	
	public Optional<User> findByFirstName(String firstName);
	
	public User getByFirstName(String firstName ) ;
	
	public User getByUserName(String userName ) ; 

	public Optional<User> findByUserNameAndPassword(String userName, String password);

	public User findById(int userId);
	
	public boolean existsByUserName(String username);
	
	public boolean existsByEmail(String email);
}
