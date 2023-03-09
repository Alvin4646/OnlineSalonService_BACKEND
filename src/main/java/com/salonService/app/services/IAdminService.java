package com.salonService.app.services;

import com.salonService.app.entity.Admin;
import com.salonService.app.exception.UserAlreadyExists;
import com.salonService.app.exception.UserNotExistsException;

public interface IAdminService {
	public Admin signUp(Admin user) throws UserNotExistsException, UserAlreadyExists;

	public Admin signIn(Admin user) throws UserNotExistsException;

	public String changePassword(Admin user) throws UserNotExistsException;

}
