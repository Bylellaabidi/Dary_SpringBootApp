package com.example.dary.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User implements UserDetails {

	private int id;

	private String username;

	private String email;
	
	private String password;
	
	private Set<Role> roles;
	
	public CustomUserDetails(final User users) {
		 super(users); 
	}
	
	public CustomUserDetails(int id, String userName,String password, String email,
			Set<Role> roles) {
		this.id = id;
		this.username = userName;
		this.password = password;
		this.email = email;
		this.roles = roles;
		
	}

	public static CustomUserDetails build(User user) {

		return new CustomUserDetails(
				user.getId(), 
				user.getUserName(), 
				user.getPassword(),
				user.getEmail(), 
				user.getRoles());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
				.collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {

		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
