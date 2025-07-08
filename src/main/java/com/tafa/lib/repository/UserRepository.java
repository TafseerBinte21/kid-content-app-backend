package com.tafa.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tafa.lib.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	
    User findByEmail(String email);
    
    boolean existsByEmail(String email);

    User findByUsername(String username);



}
