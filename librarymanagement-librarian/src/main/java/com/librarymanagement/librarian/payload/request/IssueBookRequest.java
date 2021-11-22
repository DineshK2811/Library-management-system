package com.librarymanagement.librarian.payload.request;

import lombok.Data;

@Data
public class IssueBookRequest {
	private String title;
	private String author;
	private String bookCode;
	private String studentId;
}
