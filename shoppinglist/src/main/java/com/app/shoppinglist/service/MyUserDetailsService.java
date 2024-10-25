package com.app.shoppinglist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.shoppinglist.entity.UserPrincipal;
import com.app.shoppinglist.entity.Users;
import com.app.shoppinglist.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user = userRepo.findByUsername(username); 
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found"); 
		}
		return new UserPrincipal(user);
	}

}
