package com.coderbyte.auth.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coderbyte.auth.model.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private User loadedUser;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
// 		This method will be called by spring security and will do the following sequentially:
//			1- retrieveUser - load the user
//			2- preAuthenticationChecks.check(user); - DefaultPreAuthenticationChecks: check for locked
//			3- additionalAuthenticationChecks - checks the password
//			4- postAuthenticationChecks.check(user); - DefaultPostAuthenticationChecks check for not expired credentials
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;

		User user = getInMemoryUser();
		if (user == null) {
			throw new UsernameNotFoundException(null);			
		}
		roles = Arrays.asList(new SimpleGrantedAuthority("user"));
		this.loadedUser = user;
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, roles);
	}

	private User getInMemoryUser() {
		return new User(1l, "admin", bcryptEncoder.encode("admin"));
	}

	public User getLoadedUser() {
		return loadedUser;
	}

	public void setLoadedUser(User loadedUser) {
		this.loadedUser = loadedUser;
	}
	
	

}
