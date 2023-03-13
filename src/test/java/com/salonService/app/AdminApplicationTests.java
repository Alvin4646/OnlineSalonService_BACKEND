package com.salonService.app;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salonService.app.entity.Admin;
import com.salonService.app.exception.UserAlreadyExists;
import com.salonService.app.exception.UserNotExistsException;
import com.salonService.app.repository.IAdminRepository;
import com.salonService.app.services.IAdminService;
import com.salonService.app.services.IAdminServiceImpl;

@SpringBootTest
class AdminApplicationTests {
	@Mock
	IAdminRepository adminRepository;
	@Autowired
	IAdminServiceImpl adminserviceimp;

	@InjectMocks
	IAdminService adminService = new IAdminServiceImpl();

	Admin s1, s2, s3;

	 static Admin demo() {
		Admin s = new Admin();
		s.setId((long) 1);
		s.setPassword("Monika@123");
		return s;
	}

	@BeforeEach
	 void init() {
		s1 = new Admin((long) 1, "Monika@123");
		s2 = new Admin((long) 2, "Akash@123");
		s3 = new Admin((long) 3, "Dharani@123");
	}

	Admin admin = AdminApplicationTests.demo();

	@Test
	void validAdminSignup() throws UserAlreadyExists {
		Mockito.when(adminRepository.findById(admin.getId())).thenReturn(Optional.empty());
		Assertions.assertNotNull(admin);
	
	}

	@Test
	void invalidAdminSignup() throws UserAlreadyExists {
		Mockito.when(adminRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
		UserAlreadyExists ex = Assertions.assertThrows(UserAlreadyExists.class, () -> adminService.signUp(admin));
		Assertions.assertEquals("Account with given id existed", ex.getMessage());
	}
	
	@Test
	 void validChangePassword()throws UserNotExistsException{
		Admin s4=new Admin();
		s4.setId((long) 2);
		s4.setPassword("Monika@123");
		Mockito.when(adminRepository.findById(s2.getId())).thenReturn(Optional.of(s2));
		String msg=adminService.changePassword(s2);
		Assertions.assertEquals(msg,adminService.changePassword(s4));
	}
	
	@Test
void inValidChangePassword()throws UserNotExistsException{
		Mockito.when(adminRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
		UserNotExistsException ex = Assertions.assertThrows(UserNotExistsException.class, () -> adminService.changePassword(s2));
		Assertions.assertEquals(ex.getMessage(), "User not found with id " + s2.getId());
	}
	@Test
	 void signInFailureWrongPassword(){
		Admin userR=new Admin();
		userR.setId((long) 1);
		userR.setPassword("teja@123");
	
		Mockito.when(adminRepository.findById((long)1)).thenReturn(Optional.of(userR));
		UserNotExistsException exception=Assertions.assertThrows(UserNotExistsException.class, ()->adminService.signIn(admin));
		Assertions.assertEquals("User.Wrong_Password", exception.getMessage());
	}

	
	@Test
	void SignInPassword() throws UserAlreadyExists, UserNotExistsException{
		Admin userR=new Admin();
		userR.setId((long)2);
		userR.setPassword("teja@123");
		Admin user=adminService.signUp(userR);
		Mockito.when(adminRepository.findById((long)2)).thenReturn(Optional.of(userR));
		
		//UserNotExistsException exception=Assertions.assertThrows(UserNotExistsException.class, ()->adminService.signIn(admin));
		//Assertions.assertEquals("User.Wrong_Password", exception.getMessage());
		Assertions.assertEquals(user.getPassword(),adminService.signIn(userR).getPassword());
	}
}

