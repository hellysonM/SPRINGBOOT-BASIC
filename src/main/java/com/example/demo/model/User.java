package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.example.demo.config.UserRoles;
import com.example.demo.validation.UserValidation;

@Entity
@Table(name = "user")
public class User {
	
	public User(Long id, String name, String password, String email, UserRoles role) {
		super();
		this.role = role;
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	public User() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotEmpty(groups = UserValidation.UserRegistration.class,message = "O nome não pode ficar vazio")
	@NotEmpty(message = "O nome não foi preenchido")
	private String name;
	
	
	@NotEmpty(groups = UserValidation.UserRegistration.class,message = "A senha não pode ficar vazio")
	@NotEmpty(message = "A senha não foi preenchida")
	private String password;
	
	@Column(unique = true)
	@NotEmpty(groups = UserValidation.UserRegistration.class,message = "O E-mail não pode ficar vazio")
	private String email;
	
	@NotNull(groups = UserValidation.UserRegistration.class,message = "A função não pode ficar vazia")
	private UserRoles role;
	
	public UserRoles getRole() {
		return role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
