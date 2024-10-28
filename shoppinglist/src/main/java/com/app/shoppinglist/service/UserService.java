package com.app.shoppinglist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.shoppinglist.entity.Users;
import com.app.shoppinglist.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder( 12); 
	
	public Users register(Users user) {
		System.out.println("im in service");
		user.setPassword(encoder.encode(user.getPassword()));
	return	repo.save(user); 
	}

	public String verify(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

}
