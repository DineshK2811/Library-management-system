package com.librarymanagement.dataaccess.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.librarymanagement.dataaccess.model.User;
import com.librarymanagement.dataaccess.repo.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found in this userName: "+username));
		return UserDetailsImpl.build(user);
	}

}
