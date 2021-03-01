package com.example.dary.model;

import java.io.Serializable;
import java.util.Set;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	
	private int id;
	private String username;
	private String email; 
	private Set<Role> role;

	
	
	public JwtResponse(String jwttoken, int id, String username, String email, Set<Role> role) {
		this.jwttoken = jwttoken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
	}

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJwttoken() {
		return jwttoken;
	}
	
	
}
