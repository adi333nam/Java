package com.cognizant.authorization.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.authorization.model.User;

public class AuthUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private boolean isExpired;
	private boolean isActive;
	private boolean isCredExpired;
	private boolean isEnabled;
	private List<GrantedAuthority> auths;
	
	public AuthUserDetails(User user) {
		this.userName=user.getUserName();
		this.password=user.getPassword();
		this.isActive=user.isActive();
		this.isCredExpired=user.isCredExpired();
		this.isEnabled=user.isEnabled();
		this.auths=Arrays.stream(user.getRole().split(","))
				.map(role->"ROLE_"+role)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return auths;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isActive;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isCredExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}