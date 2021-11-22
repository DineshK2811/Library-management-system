package com.librarymanagement.admin.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.admin.payload.request.LibrarianRequest;

@Service
public interface AdminService {
	
	ResponseEntity<?> addLibrarian(LibrarianRequest libRequest);
	ResponseEntity<?> viewAllLibrarian();
	ResponseEntity<?> viewLibrarianById(String librarianid);
	ResponseEntity<?> deleteLibrarianById(String librarianid);
	
}
