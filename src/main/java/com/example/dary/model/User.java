package com.example.dary.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id ; 
	@Column(name = "user_name", unique=true, nullable=false)
	private String userName;
	@Column(name = "email", unique=true, nullable=false)
	private String email ; 
	@Column(name = "password", nullable=false)
	private String password ; 
	@Column(name = "first_name")
	private String firstName ; 
	@Column(name = "last_name")
	private String lastName ; 
	@ManyToMany
	private Set<Role> roles ;
	
	public User(User user) {
		this.email = user.getEmail(); 
		this.password = user.getPassword() ; 
		this.firstName = user.getFirstName() ; 
		this.lastName = user.getLastName() ; 
		this.roles = user.getRoles() ; 
		this.id = user.getId() ;  
		
	}
	
	
	
	public User(String userName, String email, String password, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}



	public User() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
