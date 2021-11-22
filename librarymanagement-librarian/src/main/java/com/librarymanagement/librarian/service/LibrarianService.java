package com.librarymanagement.librarian.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.librarian.payload.request.BookRequest;
import com.librarymanagement.librarian.payload.request.IssueBookRequest;

@Service
public interface LibrarianService {

	ResponseEntity<?> addBook(BookRequest bookReq);	
	ResponseEntity<?> viewBooks();
	ResponseEntity<?> issueBook(IssueBookRequest issueBookReq);
	ResponseEntity<?> issuedBooks();
	ResponseEntity<?> returnBook(String studentId,BookRequest bookRequest);
}
