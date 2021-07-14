package com.system.domain.controller;


import java.util.HashMap;
import java.util.Map;

import com.system.domain.config.JwtTokenUtil;
import com.system.domain.dto.UserDTO;
import com.system.domain.models.JwtRequest;
import com.system.domain.models.JwtResponse;
import com.system.domain.models.User;
import com.system.domain.service.JwtUserDetailsService;
import com.system.swagger.SwaggerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;



@RestController
@Api(tags={SwaggerConfig.TAG_2})
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@ApiOperation(value="Autenticar", notes = "Permite Autenticar Usuário")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Token gerado"),
		@ApiResponse(code = 401, message = "Nao autorizado"),
	})
	@CrossOrigin("https://contact-book-4e449.web.app")
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {			
		try{
			final UserDetails userDetails = userDetailsService
			.loadUserByUsername(authenticationRequest.getUsername());

			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final String token = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new JwtResponse(token));
		}catch(UsernameNotFoundException e) {
			Map<String, String> res = new HashMap<>();
			res.put("status", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}

	}
	@ApiOperation(value="Registrar", notes="Registra um novo usuário")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Usuário criado"),
		@ApiResponse(code = 401, message = "Nao autorizado")
	})
	@CrossOrigin("https://contact-book-4e449.web.app")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		User createdUser = userDetailsService.save(user);
		if(createdUser == null){
			Map<String, String> res = new HashMap<>();
			res.put("status", "Usuário ja existe");
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}else {
			return new ResponseEntity<User>(createdUser,HttpStatus.CREATED);
		}
		
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}