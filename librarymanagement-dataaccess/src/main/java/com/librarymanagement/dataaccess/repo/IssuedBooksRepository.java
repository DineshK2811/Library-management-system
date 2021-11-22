package com.librarymanagement.dataaccess.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.librarymanagement.dataaccess.model.IssuedBooks;

@Repository
public interface IssuedBooksRepository extends JpaRepository<IssuedBooks, String>{

	@Query(value = "select * from issued_books ib where ib.stud_id=:studentId",nativeQuery = true)
	List<IssuedBooks> findByStud(@Param("studentId")String studentId);
	
	@Modifying
	@Transactional
	@Query(value="delete from issued_books ib where (ib.stud_id=:studentId and ib.title=:title)",nativeQuery = true)
	void deleteByStudIdAndTitle(@Param("studentId") String studentId,@Param("title") String bookTitle);

}
