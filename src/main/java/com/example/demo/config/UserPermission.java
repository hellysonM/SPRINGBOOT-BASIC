package com.example.demo.config;

public enum UserPermission {

	USER_READ("user:read"),
	USER_WRITE("user:write"),
	PRODUTO_READ("produto:read"),
	PRODUTO_WRITE("produto:write");
	
	
	private final String permission;


	UserPermission(String permission) {

		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
	
	
	
	
}