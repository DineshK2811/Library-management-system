package com.librarymanagement.auth.payload.resquest;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class StudentSignUpRequest {
	private String password;
	private String email;
	private String mobileNum;
	private Set<String> role=new HashSet<>();
	private String regNo;
	private String name;
	private String department;

}
