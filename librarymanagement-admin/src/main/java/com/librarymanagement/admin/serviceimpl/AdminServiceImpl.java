package com.librarymanagement.admin.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.librarymanagement.admin.payload.request.LibrarianRequest;
import com.librarymanagement.admin.payload.request.RoleRequest;
import com.librarymanagement.admin.service.AdminService;
import com.librarymanagement.dataaccess.model.ERole;
import com.librarymanagement.dataaccess.model.User;
import com.librarymanagement.dataaccess.payload.response.MessageResponse;
import com.librarymanagement.dataaccess.repo.UserRepository;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	@Value("${sign.up.url}")
	String signUpUrl;
	@Override
	public ResponseEntity<?> addLibrarian(LibrarianRequest libRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<LibrarianRequest> entity = new HttpEntity<LibrarianRequest>(libRequest,headers);
		Object response = restTemplate.exchange(signUpUrl,HttpMethod.POST,entity,Object.class).getBody();
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> viewAllLibrarian() {
		List<User> librarian = userRepo.findAllLibrarian(ERole.ADMIN.toString());
		RoleRequest roleRequest = new RoleRequest();
		List<RoleRequest> libRoleUser = new ArrayList<>();
		for(int i=0;i<librarian.size();i++) {
			String roles=librarian.get(i).getRole().iterator().next().getName().name();
			if(roles.equals(ERole.LIBRARIAN.name())) {
				roleRequest = RoleRequest.builder().id(librarian.get(i).getId())
						.userName(librarian.get(i).getUserName()).email(librarian.get(i).getEmail())
						.password(librarian.get(i).getPassword()).mobileNum(librarian.get(i).getMobileNum())
						.role(librarian.get(i).getRole()).build();
				libRoleUser.add(roleRequest);
			}
		}
		return ResponseEntity.ok(new MessageResponse("Librarians list!", HttpStatus.OK.value(), libRoleUser));
	}

	@Override
	public ResponseEntity<?> viewLibrarianById(String librarianid) {
		User libId = userRepo.findById(librarianid).get();
		return ResponseEntity.ok(new MessageResponse("Librarian details!",HttpStatus.OK.value(),libId));
	}

	@Override
	public ResponseEntity<?> deleteLibrarianById(String librarianid) {
		User libId = userRepo.findById(librarianid).get();
		userRepo.delete(libId);
		return ResponseEntity.ok(new MessageResponse("Librarian deleted successfully!",HttpStatus.OK.value()));
	}

}
