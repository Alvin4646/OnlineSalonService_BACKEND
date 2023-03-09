package com.salonService.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Admin;
import com.salonService.app.exception.UserAlreadyExists;
import com.salonService.app.exception.UserNotExistsException;
import com.salonService.app.repository.IAdminRepository;

@Service
public class IAdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminRepository iadminRepo;


	@Override
	public Admin signUp(Admin newAdmin) throws UserNotExistsException, UserAlreadyExists {
		Optional<Admin> repUser = iadminRepo.findById(newAdmin.getId());
		if (repUser.isPresent())
			throw new UserAlreadyExists("Account with given id existed");
		else
			iadminRepo.save(newAdmin);
		return newAdmin;
	}

	@Override
	public Admin signIn(Admin user) throws UserNotExistsException {
		Optional<Admin> repUser = iadminRepo.findById(user.getId());
		Admin u = repUser.orElseThrow(() -> new UserNotExistsException("User not found with id " + user.getId()));
		if (!u.getPassword().equals(user.getPassword())) {
			throw new UserNotExistsException("User.Wrong_Password");
		}
		return user;
	}

	@Override
	public String changePassword(Admin admin) throws UserNotExistsException {
		Optional<Admin> repUser = iadminRepo.findById(admin.getId());
		Admin u = repUser.orElseThrow(() -> new UserNotExistsException("User not found with id " + admin.getId()));
		u.setPassword(admin.getPassword());
		iadminRepo.save(u);
		return "Password changed successfully";
	}

}
