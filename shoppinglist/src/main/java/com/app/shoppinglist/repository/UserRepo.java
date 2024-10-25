package com.app.shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.shoppinglist.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
Users findByUsername(String username); 
	
}
