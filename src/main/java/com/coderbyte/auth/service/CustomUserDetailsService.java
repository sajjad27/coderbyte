package com.coderbyte.auth.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coderbyte.auth.model.entity.User;
import com.coderbyte.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	private User loadedUser;

	@Override
// 		This method will be called by spring secuarity and will do the following sequentailly:
//			1- retrieveUser - load the user
//			2- preAuthenticationChecks.check(user); - DefaultPreAuthenticationChecks: check for locked
//			3- additionalAuthenticationChecks - checks the password
//			4- postAuthenticationChecks.check(user); - DefaultPostAuthenticationChecks check for not expired credentials
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;

		User user = userRepository.findByUsernameIgnoreCase(username);
		if (user == null) {
			throw new UsernameNotFoundException(null);			
		}
		roles = Arrays.asList(new SimpleGrantedAuthority("user"));
		this.loadedUser = user;
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, user.getIsActivated() == 1, true, roles);
	}

	public User getLoadedUser() {
		return loadedUser;
	}

	public void setLoadedUser(User loadedUser) {
		this.loadedUser = loadedUser;
	}
	
	

}
