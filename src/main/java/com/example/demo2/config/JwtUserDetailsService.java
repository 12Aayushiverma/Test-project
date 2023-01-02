package com.example.demo2.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo2.entities.UserMst;
import com.example.demo2.repositories.UserRepository;

@Service
@ComponentScan({ "com.example.demo2.repositories" })
public class JwtUserDetailsService implements UserDetailsService{

	
	

	@Autowired
	private UserRepository userRepository;
	
	

	private final static Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("Start UserDetailsServiceImpl in loadUserByUsername");

		Optional<UserMst> userDtlsFromDb = userRepository.findByEmailOrMobileNumber(username, username);

		log.info("Fetching user login data using database findByEmail");

		if (!userDtlsFromDb.isPresent()) {
			log.error("User not found with email: {}", username);
			throw new UsernameNotFoundException("User not found with userName: " + username);
		}
 
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+userDtlsFromDb.get().getRole());
		list.add(role);		
		log.info("userDatails return for loadUserByUsername");
		return new org.springframework.security.core.userdetails.User(userDtlsFromDb.get().getEmail(),
				userDtlsFromDb.get().getPassword(), list);
	}

}
