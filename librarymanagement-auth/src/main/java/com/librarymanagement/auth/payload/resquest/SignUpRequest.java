package com.librarymanagement.auth.payload.resquest;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class SignUpRequest {
	
	private String password;
	private String email;
	private String mobileNum;
	private Set<String> role=new HashSet<>();
}
