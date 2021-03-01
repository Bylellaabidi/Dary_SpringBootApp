package com.example.dary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dary.model.Role;
import com.example.dary.model.User;

@Repository
public interface RoleRepository extends JpaRepository<Role , Integer>  {

	public Role getByRole(String role);
	
	public Optional<Role> findByRole(String role);
}
