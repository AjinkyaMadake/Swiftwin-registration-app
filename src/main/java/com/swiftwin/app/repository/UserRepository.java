package com.swiftwin.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiftwin.app.entity.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer>{

	
	
}
