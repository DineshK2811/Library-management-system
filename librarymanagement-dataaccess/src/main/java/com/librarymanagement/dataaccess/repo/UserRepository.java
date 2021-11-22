package com.librarymanagement.dataaccess.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.librarymanagement.dataaccess.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUserName(String userName);

	boolean existsByUserName(String email);
	
	@Query(value="select * from users u where u.id not in(select ur.user_id from user_roles ur where ur.role_id in(select r.id from roles r where r.name=:roleAdmin))",nativeQuery = true)
	List<User> findAllLibrarian(@Param("roleAdmin")String roleAdmin);

	Optional<User> findById(String librarianid);
}
