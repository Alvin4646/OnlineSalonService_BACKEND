package com.salonService.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.LoginCredentials;
import com.salonService.app.entity.User;
import com.salonService.app.exception.InvalidUserException;
import com.salonService.app.repository.IUserRepository;
import com.salonService.app.util.JWTUtils;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	

	@Autowired
	IUserRepository userRepository;

	User user;

	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginCredentials request,HttpServletResponse response) throws Exception {
		user = userRepository.findByUserName(request.getUsername());
		if (user == null) {
			throw new InvalidUserException("User not found with username: " + request.getUsername());
		} 
		if (!(user.getPassword().equals(request.getPassword())))
			throw new InvalidUserException("Invalid Password");
		String token= JWTUtils.generateToken(user.getUserId().toString());
		response.setHeader("Authorization", token);
		response.addHeader("token", token);
		response.addHeader("Access-Control-Expose-Headers", "token");
		return ResponseEntity.ok().body(user);
	}

}