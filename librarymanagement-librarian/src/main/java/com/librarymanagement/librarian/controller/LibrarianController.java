package com.librarymanagement.librarian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.librarian.payload.request.BookRequest;
import com.librarymanagement.librarian.payload.request.IssueBookRequest;
import com.librarymanagement.librarian.service.LibrarianService;
@CrossOrigin
@RestController
@RequestMapping("/api/librarian")
public class LibrarianController {
	
	@Autowired
	LibrarianService librarianService;
	
	@PostMapping("/add/book")
	public ResponseEntity<?> addBook(@RequestBody BookRequest bookReq){
		return librarianService.addBook(bookReq);
	}
	
	@GetMapping("/view/all/books")
	public ResponseEntity<?> viewBooks(){
		return librarianService.viewBooks();
	}
	
	@PostMapping("/issue/books")
	public ResponseEntity<?> issueBook(@RequestBody IssueBookRequest issueBookReq){
		return librarianService.issueBook(issueBookReq);
	}
	
	@GetMapping("/issued/books/list")
	public ResponseEntity<?> issuedBooks(){
		return librarianService.issuedBooks();
	}
	
	@DeleteMapping("/return/book/{studentId}")
	public ResponseEntity<?> returnBook(@PathVariable(value = "studentId") String studentId,@RequestBody BookRequest bookRequest){
		return librarianService.returnBook(studentId, bookRequest);
	}

}
