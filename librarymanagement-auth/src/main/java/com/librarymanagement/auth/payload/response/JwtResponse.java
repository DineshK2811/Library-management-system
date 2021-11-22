package com.librarymanagement.auth.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	private String id;
	private String username;
	private String email;
	private long mobileNum;
	private List<String> roles;

	public JwtResponse(String accessToken,String id,String userName,String email,long mobileNum,List<String> role) {
		this.accessToken=accessToken;
		this.id=id;
		this.username=userName;
		this.email=email;
		this.mobileNum=mobileNum;
		this.roles=role;
	}

}
