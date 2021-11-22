package com.librarymanagement.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.student.payload.request.BorrowBookRequest;
import com.librarymanagement.student.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@GetMapping("/books")
	public ResponseEntity<?> viewBooks(){
		return studentService.viewBooks();
	}
	
	@GetMapping("/view/lended/books/{studentId}")
	public ResponseEntity<?> viewLendedBooks(@PathVariable(value="studentId") String studentId){
		return studentService.viewLendedBooks(studentId);
	}
	
	@PostMapping("/lend/book/{studentId}")
	public ResponseEntity<?> lendBook(@PathVariable(value="studentId") String studentId,@RequestBody BorrowBookRequest bookRequest){
		return studentService.lendBook(studentId, bookRequest);
	}
	
	@DeleteMapping("/return/book/{studentId}")
	public ResponseEntity<?> returnBook(@PathVariable(value="studentId") String studentId,@RequestBody BorrowBookRequest bookRequest){
		return studentService.bookReturn(studentId, bookRequest);
	}
  
}
