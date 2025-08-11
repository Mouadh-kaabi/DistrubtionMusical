package com.example.demo.payload.response;

import java.util.List;

import com.example.demo.entities.Role;

public class JwtResponse {

	
	private String token;
	private String type = "Bearer";
	private Integer id;
	/*private String username;*/
	private String email;
	private List<String> roles;

	
	
	
	
	public JwtResponse(String accessToken, Integer id, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
	
	
	







	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

/*	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}*/

}
