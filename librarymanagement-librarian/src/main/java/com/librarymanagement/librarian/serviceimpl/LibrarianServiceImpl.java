package com.librarymanagement.librarian.serviceimpl;

import java.util.List;

import org.joda.time.DateTime;
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
import com.librarymanagement.librarian.payload.request.BookRequest;
import com.librarymanagement.librarian.payload.request.IssueBookRequest;
import com.librarymanagement.librarian.service.LibrarianService;

@Service
public class LibrarianServiceImpl implements LibrarianService{
	
	@Autowired
	BooksRepository bookRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	IssuedBooksRepository issuedBookRepo;

	@Override
	public ResponseEntity<?> addBook(BookRequest bookReq) {
		int noOfBooks=1;
		Books book=new Books();
		boolean flag;
		flag=bookRepo.existsByTitle(bookReq.getTitle());
		if(flag) {
			book=bookRepo.findByTitle(bookReq.getTitle()).get();
			int bookCount=book.getNoOfBooksAval()+noOfBooks;
			book=Books.builder().id(book.getId()).title(bookReq.getTitle()).author(bookReq.getAuthor())
					.bookCode(bookReq.getBookCode()).noOfBooksAval(bookCount).build();
			book=bookRepo.save(book);
		}else {
		 book = Books.builder().title(bookReq.getTitle())
				.author(bookReq.getAuthor()).bookCode(bookReq.getBookCode())
				.noOfBooksAval(noOfBooks).build();
		 book=bookRepo.save(book);
		}
		return ResponseEntity.ok(new MessageResponse("Book saved Successfully",HttpStatus.OK.value(),book));
	}

	@Override
	public ResponseEntity<?> viewBooks() {
		List<Books> books = bookRepo.findAll();
		return ResponseEntity.ok(new MessageResponse("List of all books!",HttpStatus.OK.value(),books));
	}

	@Override
	public ResponseEntity<?> issueBook(IssueBookRequest issueBookReq) {
		IssuedBooks issuedBook = new IssuedBooks();
		Student student = studentRepo.findById(issueBookReq.getStudentId()).get();
		 issuedBook = IssuedBooks.builder().title(issueBookReq.getTitle())
				.author(issueBookReq.getAuthor()).bookCode(issueBookReq.getBookCode())
				.stud(student).build();
		student.getIssuedBooks().add(issuedBook);
		issuedBook=issuedBookRepo.save(issuedBook);
		return ResponseEntity.ok(new  MessageResponse("Book issued successfully!", HttpStatus.OK.value(), issuedBook));
	}

	@Override
	public ResponseEntity<?> issuedBooks() {
		List<IssuedBooks> issuedBooks = issuedBookRepo.findAll();
		return ResponseEntity.ok(new MessageResponse("List of Issued Books",HttpStatus.OK.value(),issuedBooks));
	}

	@Override
	public ResponseEntity<?> returnBook(String studentId, BookRequest bookRequest) {
		String bookTitle=bookRequest.getTitle();
		 issuedBookRepo.deleteByStudIdAndTitle(studentId,bookTitle);
//		System.out.println(issuedBk);
		return ResponseEntity.ok(new MessageResponse(bookTitle+" Book returned!",HttpStatus.OK.value()));
		
	}

}
