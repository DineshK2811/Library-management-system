package com.librarymanagement.student.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.student.payload.request.BorrowBookRequest;

@Service
public interface StudentService {

	ResponseEntity<?> viewBooks();
	ResponseEntity<?> viewLendedBooks(String studentId);
	ResponseEntity<?> lendBook(String studentId,BorrowBookRequest bookRequest);
	ResponseEntity<?> bookReturn(String studentId,BorrowBookRequest bookRequest);
} 
