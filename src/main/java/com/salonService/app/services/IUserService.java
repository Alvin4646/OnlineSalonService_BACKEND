package com.salonService.app.services;

import com.salonService.app.entity.User;

public interface IUserService {
	public User signIn(User user);
	public User signOut(User user);
	public User changePassword(Integer id, String changePassword);
	public User updateCredentials(User user, String userName, String password);
	public User getUserById(Integer id);
}