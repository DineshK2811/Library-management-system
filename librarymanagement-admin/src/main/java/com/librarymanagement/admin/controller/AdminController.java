package com.librarymanagement.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.admin.payload.request.LibrarianRequest;
import com.librarymanagement.admin.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@PostMapping("/save/librarian")
	public ResponseEntity<?> saveLibrarian(@RequestBody LibrarianRequest req){
		return adminService.addLibrarian(req);
	}
	
	@GetMapping("/list/all/librarians")
	public ResponseEntity<?> listAllLibrarian(){
		return adminService.viewAllLibrarian();
	}
	
	@GetMapping("/librarian/details/{librarianId}")
	public ResponseEntity<?> viewLibrarianDetails(@PathVariable(value = "librarianId") String librarianId){
		return adminService.viewLibrarianById(librarianId);
	}
	
	@DeleteMapping("/delete/librarian/{librarianId}")
	public ResponseEntity<?> deleteLibrarianById(@PathVariable(value="librarianId") String librarianId){
		return adminService.deleteLibrarianById(librarianId);
	}
}
