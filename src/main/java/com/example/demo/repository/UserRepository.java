package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Produto;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByName(String username);
	User findByEmail(String email);
	
	
	 @Query(value = "SELECT * FROM User WHERE name like ?1",
			    countQuery = "SELECT count(*) FROM User WHERE name like ?1",
			    nativeQuery = true)
	Page<User> findByName(String name,Pageable pageable);
	 
	
}
