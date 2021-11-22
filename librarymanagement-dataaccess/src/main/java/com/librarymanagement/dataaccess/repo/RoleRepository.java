package com.librarymanagement.dataaccess.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.dataaccess.model.ERole;
import com.librarymanagement.dataaccess.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

	boolean existsByName(ERole role);

	Optional<Role> findByName(ERole role);

}
