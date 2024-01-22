package com.jsp.ums.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jsp.ums.Entity.User;
import com.jsp.ums.Repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomUserDetails customUserDetails;
	
	@Autowired
	private UserRepository userRepository;

	public CustomUserDetailsService(CustomUserDetails customUserDetails, UserRepository userRepository) {
		super();
		this.customUserDetails = customUserDetails;
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		return new CustomUserDetails(user);
		 
	}

	
	
}
