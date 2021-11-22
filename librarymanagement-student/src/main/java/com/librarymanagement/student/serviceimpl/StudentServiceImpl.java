package com.librarymanagement.student.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.dataaccess.model.Books;
import com.librarymanagement.dataaccess.model.IssuedBooks;
import com.librarymanagement.dataaccess.model.Student;
import com.librarymanagement.dataaccess.payload.response.MessageResponse;
import com.librarymanagement.dataaccess.repo.BooksRepository;
import com.librarymanagement.dataaccess.repo.IssuedBooksRepository;
import com.librarymanagement.dataaccess.repo.StudentRepository;
import com.librarymanagement.student.payload.request.BorrowBookRequest;
import com.librarymanagement.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	BooksRepository booksRepo;
	@Autowired
	IssuedBooksRepository issuedBooksRepo;
	@Autowired
	StudentRepository studentRepo;
	@Override
	public ResponseEntity<?> viewBooks() {
		List<Books> books = booksRepo.findAll();
		return ResponseEntity.ok(new MessageResponse("Books List!", HttpStatus.OK.value(), books));
	}
	@Override
	public ResponseEntity<?> viewLendedBooks(String studentId) {
		List<IssuedBooks> issuedBooks = issuedBooksRepo.findByStud(studentId);
		return ResponseEntity.ok(new MessageResponse("You are having these books!",HttpStatus.OK.value(),issuedBooks));
	}
	@Override
	public ResponseEntity<?> lendBook(String studentId,BorrowBookRequest bookRequest) {
		Student student = studentRepo.findById(studentId).get();
		IssuedBooks borrowBook = IssuedBooks.builder().title(bookRequest.getTitle())
				.author(bookRequest.getAuthor()).bookCode(bookRequest.getBook_code())
				.stud(student).build();
		borrowBook = issuedBooksRepo.save(borrowBook);
		return ResponseEntity.ok(new MessageResponse("You're lend this book now!",HttpStatus.OK.value(),borrowBook));
	}
	@Override
	public ResponseEntity<?> bookReturn(String studentId, BorrowBookRequest bookRequest) {
		String bookTitle=bookRequest.getTitle();
		 issuedBooksRepo.deleteByStudIdAndTitle(studentId,bookTitle);
//		System.out.println(issuedBk);
		return ResponseEntity.ok(new MessageResponse(bookTitle+" Book returned!",HttpStatus.OK.value()));
	}

}
