package com.example.demo.config;


import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import com.example.demo.config.UserPermission;

public enum UserRoles {

	ADMIN(Arrays.asList(UserPermission.USER_READ,
			UserPermission.USER_WRITE,
			UserPermission.PRODUTO_READ,
			UserPermission.PRODUTO_WRITE)),
	
	FUNCIONARIO(Arrays.asList(
			UserPermission.PRODUTO_READ,
			UserPermission.PRODUTO_WRITE));

	private final List<UserPermission> permissions;

	private UserRoles(List<UserPermission> permissions) {
		this.permissions = permissions;
	}

	public List<UserPermission> getPermissions() {
		return permissions;
	}
	
	public List<SimpleGrantedAuthority> getGrantedAuthorities(){
		List<SimpleGrantedAuthority> lista = getPermissions().stream()
		.map(permission-> new SimpleGrantedAuthority(permission.getPermission()) )
		.collect(Collectors.toList());
		lista.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		
		return lista;
	}
	
	
}