package com.swiftwin.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.swiftwin.app.entity.UserDtls;
import com.swiftwin.app.repository.UserRepository;


public class CustomUserDtlsService implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//depennding upon given input email it checks that email related user is available or not in db
		UserDtls u=repo.findByEmail(username);
		
		// to check user is available or not
		if(u==null)
		{
			throw new UsernameNotFoundException("NO USER");
		}
		else {
		return new CustomUserDtls(u);
		}
	
	}
	
}