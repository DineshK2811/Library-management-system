package com.librarymanagement.student.payload.request;

import lombok.Data;

@Data
public class BorrowBookRequest {

	private String title;
	private String author;
	private String book_code;
}
