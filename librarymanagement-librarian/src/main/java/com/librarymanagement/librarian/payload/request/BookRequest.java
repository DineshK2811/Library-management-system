package com.librarymanagement.librarian.payload.request;

import lombok.Data;

@Data
public class BookRequest {
	private String title;
	private String author;
	private String bookCode;

}
