package com.librarymanagement.dataaccess.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="issued_books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssuedBooks {
	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	private String id;
	private String title;
	private String author;
	@Column(name="book_code")
	private String bookCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="stud_id",referencedColumnName = "userId")
	private Student stud;
//	@Builder.Default
//	private DateTime issued ;
}
