package com.librarymanagement.auth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.auth.payload.resquest.SignInRequest;
import com.librarymanagement.auth.payload.resquest.SignUpRequest;
import com.librarymanagement.auth.payload.resquest.StudentSignUpRequest;
import com.librarymanagement.dataaccess.exceptions.RoleNotFoundException;

@Service
public interface AuthService {
	
	ResponseEntity<?> signInUser(SignInRequest signInReq);
	ResponseEntity<?> signUpUser(SignUpRequest signUpReq);	
	ResponseEntity<?> studentSignUp(StudentSignUpRequest studentSignUpRequest);
}
