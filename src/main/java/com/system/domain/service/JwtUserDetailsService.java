package com.system.domain.service;

import java.util.ArrayList;

import com.system.domain.dto.UserDTO;
import com.system.domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.system.domain.models.User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	public com.system.domain.models.User save(UserDTO user) {
		if(userRepository.findByUsername(user.getUsername()) != null) {
			return null;
		}
		com.system.domain.models.User newUser = new com.system.domain.models.User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}
}