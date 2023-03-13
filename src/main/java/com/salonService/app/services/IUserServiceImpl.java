package com.salonService.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.User;
import com.salonService.app.exception.UserNotFoundException;
import com.salonService.app.repository.IUserRepository;



@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	IUserRepository repository;

	@Override
	public User signIn(User user) {
	
		User loggedInUser = repository.getUserByUsernameAndPassword(user.getUserName(), user.getPassword());
		if (loggedInUser != null)
			return loggedInUser;
		throw new UserNotFoundException("User not found in database");

	}

	@Override
	public User changePassword(Integer id, String changedPassword) {

		if (repository.existsById(id)) {

			User userFromDBTable = repository.findById(id).get();
			userFromDBTable.setPassword(changedPassword);
			return userFromDBTable;

		} else {
			return null;
		}

	}

	@Override
	public User updateCredentials(User user, String userName, String password) {
		user.setUserName(userName);
		user.setPassword(password);
		repository.save(user);
		return user;
	}

	

	@Override
	public User signOut(User user) {
	
		return null;
	}

	@Override
	public User getUserById(Integer id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
}