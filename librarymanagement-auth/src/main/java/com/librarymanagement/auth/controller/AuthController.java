package com.librarymanagement.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.auth.payload.resquest.SignInRequest;
import com.librarymanagement.auth.payload.resquest.SignUpRequest;
import com.librarymanagement.auth.payload.resquest.StudentSignUpRequest;
import com.librarymanagement.auth.service.AuthService;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@RequestBody SignInRequest signInReq){
		return authService.signInUser(signInReq);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest) {
		return authService.signUpUser(signUpRequest);
	}
	
	@PostMapping("/student/signup")
	public ResponseEntity<?> studentSignUp(@RequestBody StudentSignUpRequest studentSignUpRequest){
		return authService.studentSignUp(studentSignUpRequest);
	}
	
}
