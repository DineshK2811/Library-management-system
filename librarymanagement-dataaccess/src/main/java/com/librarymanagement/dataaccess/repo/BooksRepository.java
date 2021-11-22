package com.librarymanagement.dataaccess.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.dataaccess.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, String>{

	boolean existsByTitle(String title);

	Optional<Books> findByTitle(String title);

}
