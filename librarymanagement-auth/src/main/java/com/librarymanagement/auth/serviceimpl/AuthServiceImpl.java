package com.librarymanagement.auth.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.librarymanagement.auth.payload.response.JwtResponse;
import com.librarymanagement.auth.payload.resquest.SignInRequest;
import com.librarymanagement.auth.payload.resquest.SignUpRequest;
import com.librarymanagement.auth.payload.resquest.StudentSignUpRequest;
import com.librarymanagement.auth.service.AuthService;
import com.librarymanagement.dataaccess.exceptions.UserNotFoundException;
import com.librarymanagement.dataaccess.model.ERole;
import com.librarymanagement.dataaccess.model.Role;
import com.librarymanagement.dataaccess.model.Student;
import com.librarymanagement.dataaccess.model.User;
import com.librarymanagement.dataaccess.payload.response.MessageResponse;
import com.librarymanagement.dataaccess.repo.RoleRepository;
import com.librarymanagement.dataaccess.repo.StudentRepository;
import com.librarymanagement.dataaccess.repo.UserRepository;
import com.librarymanagement.dataaccess.security.jwt.JwtUtils;
import com.librarymanagement.dataaccess.security.service.UserDetailsImpl;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	StudentRepository studentRepo;
	@Override
	public ResponseEntity<?> signInUser(SignInRequest signInReq) {
		if(!userRepo.existsByUserName(signInReq.getUserName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("UserName not exists", HttpStatus.BAD_REQUEST.value()));
		}
		User user = userRepo.findByUserName(signInReq.getUserName()).get();
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signInReq.getUserName(), signInReq.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwtToken = jwtUtils.generateToken(authentication);
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			if(Objects.isNull(userDetails)) {
				throw new UserNotFoundException();	
			}
			List<String> roles = userDetails.getAuthorities().stream().map(role->role.getAuthority()).collect(Collectors.toList());
			return ResponseEntity.ok(new MessageResponse("User successfully logged in", HttpStatus.OK.value(),
					new JwtResponse(jwtToken, userDetails.getId(), userDetails.getUsername(),userDetails.getEmail() , userDetails.getMobileNum(), roles)));
		}catch(Exception e) {
			return ResponseEntity.ok(new MessageResponse("Bad credentials",HttpStatus.UNAUTHORIZED.value(),e.getMessage()));
		}
	
	}

	@Override
	public ResponseEntity<?> signUpUser(SignUpRequest signUpReq)  {
		User user = User.builder().email(signUpReq.getEmail()).mobileNum(Long.valueOf(signUpReq.getMobileNum()))
				.userName(signUpReq.getEmail()).password(passwordEncoder.encode(signUpReq.getPassword())).build();
		if(userRepo.existsByUserName(signUpReq.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username already exists",HttpStatus.BAD_REQUEST.value()));
		}
		Set<String> userRole = signUpReq.getRole();
		
		Set<Role> role = new HashSet<>();
		
		user.setRole(getRole(userRole, role));
		user=userRepo.save(user);
		return ResponseEntity.ok(new MessageResponse("User Registered Successfully",HttpStatus.OK.value(),user));
	}

	private Set<Role> getRole(Set<String> userRole, Set<Role> role) {
		if(Objects.isNull(userRole) || userRole.isEmpty()) {
			Role defaultRole=roleRepo.findByName(ERole.LIBRARIAN).get();
			role.add(defaultRole);
		}else {
			userRole.forEach(roles->{
//			String roles =role.iterator().next().getName().name();
				switch(roles) {
				case "ADMIN":
					Role adminRole = roleRepo.findByName(ERole.ADMIN).get();
					role.add(adminRole);
					break;
				case "LIBRARIAN":
					Role libRole = roleRepo.findByName(ERole.LIBRARIAN).get();
					role.add(libRole);
					break;
				case "STUDENT":
					Role stuRole = roleRepo.findByName(ERole.STUDENT).get();
					role.add(stuRole);
					break;
				}
			});
		}
		return role;
	}

	@Override
	public ResponseEntity<?> studentSignUp(StudentSignUpRequest studentSignUpRequest) {
		User user = User.builder().email(studentSignUpRequest.getEmail())
				.mobileNum(Long.parseLong(studentSignUpRequest.getMobileNum()))
				.userName(studentSignUpRequest.getEmail()).password(passwordEncoder.encode(studentSignUpRequest.getPassword()))
				.build();
		if(userRepo.existsByUserName(studentSignUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username already exists",HttpStatus.BAD_REQUEST.value()));
		}
		Set<String> stuRole=studentSignUpRequest.getRole();
		Set<Role> role = new HashSet<>();
		user.setRole(getRole(stuRole, role));
		user=userRepo.save(user);
		Student student = Student.builder().regNo(studentSignUpRequest.getRegNo())
				.name(studentSignUpRequest.getName()).department(studentSignUpRequest.getDepartment())
				.user(user).build();
		student = studentRepo.save(student);
		return ResponseEntity.ok(new MessageResponse("Student Registration Successful!",HttpStatus.OK.value(),user));
	}

}
