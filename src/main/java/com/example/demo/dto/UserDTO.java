package com.example.demo.dto;

import com.example.demo.config.UserRoles;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private UserRoles role;
	
	public UserDTO(User user) {
	
		this.name = user.getName(); 
		this.email = user.getEmail();
		this.role = user.getRole();
		this.id = user.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}
	
	
	
	
}
