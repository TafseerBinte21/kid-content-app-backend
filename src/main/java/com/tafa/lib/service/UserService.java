package com.tafa.lib.service;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tafa.lib.entity.User;
import com.tafa.lib.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User entity) {
		return userRepository.save(entity);
	}
	
	public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
	
	public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	public User registerUser(User user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already in use!");
           
        }
       
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
        		Collections.emptyList());
	}

}
