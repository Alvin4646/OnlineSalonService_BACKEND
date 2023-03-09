package com.salonService.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.User;
import com.salonService.app.exception.InvalidUserException;
import com.salonService.app.exception.UserNotFoundException;
import com.salonService.app.repository.IUserRepository;
import com.salonService.app.services.IUserServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("user")

public class UserController {

	@Autowired
	IUserServiceImpl service;

	IUserRepository repository;

	@GetMapping(value = "/signin")
	public ResponseEntity<String> signIn(@RequestBody User user, HttpServletRequest request) {
		
		try {

			service.signIn(user);
			return new ResponseEntity<String>("Login Successfull!", HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<String>("Login Unsuccessfull!", HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getUser/{uid}")

	public ResponseEntity<?> getUser(@PathVariable("uid") Integer id, HttpServletRequest request) {
		

		User user = service.getUserById(id);
		if (user == null) {
			throw new UserNotFoundException("Appointment with appointment id:" + id + "not found");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("signout")
	public ResponseEntity<String> signOut(HttpServletRequest request) {
		

		
		return new ResponseEntity<String>("Logout Successfull!", HttpStatus.OK);

	}


	@PatchMapping("/{id}/{userName}/{password}")
	public ResponseEntity<?> updateCredentials(@PathVariable("id") Integer userId, @PathVariable("userName") String userName,
			@PathVariable("password") String password,HttpServletRequest request) {
		User user = checkUserLoggedIn(request);
		if(!user.getRole().equals("admin"))
			throw new InvalidUserException("Invalid Operation");
		
		User userToBeUpdated = service.getUserById(userId);

		User updatedUser = service.updateCredentials(userToBeUpdated, userName, password);
		if (updatedUser != null) {
			return new ResponseEntity<String>("Credentials updated successfully", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Not able to update credentials", HttpStatus.NOT_FOUND);

	}
	
	public User checkUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			throw new InvalidUserException("You should login first");
		}
		String userName = (String) session.getAttribute("name");
		User user = repository.findByUserName(userName);
		return user;
				
	}

}