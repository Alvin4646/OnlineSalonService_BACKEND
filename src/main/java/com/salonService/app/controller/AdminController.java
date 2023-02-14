package com.salonService.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.Admin;
import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.SalonService;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.exception.ServiceAlreadyExistsException;
import com.salonService.app.exception.UserAlreadyExists;
import com.salonService.app.exception.UserNotExistsException;
import com.salonService.app.services.IAdminService;
import com.salonService.app.services.IAppointmentService;
import com.salonService.app.services.ISalonService;

@RestController
@Validated

public class AdminController {

	@Autowired
	private IAdminService iAdminService;

	@Autowired
	private ISalonService salonService;
	@Autowired
	private IAppointmentService iAppointmentService;

	@Autowired
	private Environment environment;

	@PostMapping(value = "/admin_SignUp")
	public String signUp(@RequestBody @Valid Admin admin) throws UserNotExistsException, UserAlreadyExists {
		iAdminService.signUp(admin);
		return "Account created successfully";

	}
 
	@PutMapping(value = "/adminChangePassword")
	public String changePassword(@RequestBody @Valid Admin admin) throws UserNotExistsException {
		return iAdminService.changePassword(admin);
	}

	@PostMapping(value = "/admin_SignIn")
	public String signIn1(@RequestBody @Valid Admin admin) throws UserNotExistsException {
		iAdminService.signIn(admin);
		return "Signed in successfully";
	}

	@DeleteMapping(value = "/adminDeleteService/{serviceId}")
	public ResponseEntity<String> removeService(@PathVariable Long serviceId) throws SalonServiceNotFoundException {
		salonService.removeService(serviceId);
		String successMessage = environment.getProperty("serviceDeletedSuccessfully");

		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

	@PutMapping(value = "/adminUpdateService/{serviceId}")
	public ResponseEntity<String> updateService(@PathVariable Long serviceId, @RequestBody SalonService service)
			throws Exception {
		salonService.updateService(serviceId, service);
		String successMessage = environment.getProperty("Updated Successfully");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

	@PostMapping(value = "/adminAddService")
	public ResponseEntity<String> addService(@Valid @RequestBody SalonService s) throws ServiceAlreadyExistsException {
		salonService.addService(s);
		String successMessage = environment.getProperty("service successfully");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

		// getProperty:- The Java platform uses a Properties object to provide
		// information about the local system and configuration.
		// Class Environment:- A container (holder) for an exception that is used in
		// Request operations to make exceptions available to the client.
	}

	@GetMapping("/adminAppointmentById/{id}")
	public Appointment findAppointmentById(@PathVariable long id) throws AppointmentException {
		return iAppointmentService.getAppointmentById(id);
	}

	@GetMapping("/adminAppointments")
	public List<Appointment> findAllEmployee() throws AppointmentException {
		return iAppointmentService.getAllAppointments();
	}

	@DeleteMapping("/adminDeleteAppointment/{id}")
	public String deleteAppointment(@PathVariable int id) throws AppointmentException {
		return iAppointmentService.removeAppointment(id);
	}

}
