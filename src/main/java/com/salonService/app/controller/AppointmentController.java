package com.salonService.app.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Appointment.AppointmentStatus;
import com.salonService.app.entity.Payment;
import com.salonService.app.entity.SalonService;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.CustomerNotFoundException;
import com.salonService.app.exception.JwtTokenMalformedException;
import com.salonService.app.exception.JwtTokenMissingException;
import com.salonService.app.services.IAppointmentService;
import com.salonService.app.util.JWTUtils;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
	@Autowired
	private IAppointmentService iAppointmentService;
	

	@PostMapping("/appointment/{cid}")
	public Appointment addAppointmentToCustomer(@Valid @RequestBody Appointment appointment, @PathVariable int cid,HttpServletRequest request)
			throws Exception {
		JWTUtils.validateToken(request);
		return this.iAppointmentService.addAppointmentToCustomer(appointment, cid); 
	}
 
	@GetMapping("/appointment/{aid}")
	public Appointment findAppointmentById(@PathVariable Long aid,HttpServletRequest request) throws AppointmentException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return iAppointmentService.getAppointmentById(aid);
	}

	@GetMapping("/appointments")
	public List<Appointment> findAllAppointments(HttpServletRequest request) throws AppointmentException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return iAppointmentService.getAllAppointments();

	}

	@GetMapping("/appointment/date/{date}")
	public List<Appointment> findAppointmentByDate(@PathVariable String date,HttpServletRequest request) throws AppointmentException {
		
		LocalDate dateToFind = LocalDate.parse(date);
		return iAppointmentService.getAppointmentByDate(dateToFind);
	}

	@PutMapping("/appointment/{id}")
	public Appointment updateAppointments(@Valid @RequestBody Appointment appointment, @PathVariable Long id,HttpServletRequest request)
			throws AppointmentException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return iAppointmentService.updateAppointment(id, appointment);
	}

	@GetMapping("/appointments/status/{status}")
	public List<Appointment> getOpenAppointments(@PathVariable AppointmentStatus status,HttpServletRequest request) throws AppointmentException {
		
		return iAppointmentService.getOpenAppointments(status);
	}

	@PutMapping("/appointment/date/{id}")
	public Appointment updateAppointmentDate(@RequestBody LocalDate preferredDate, @PathVariable Long id)
			throws AppointmentException {
		return iAppointmentService.updateAppointmentDate(id, preferredDate);
	}


	@GetMapping("/appointment/services/{appointmentId}")
	public List<SalonService> getAllServices(@PathVariable long appointmentId) throws AppointmentException {
		return iAppointmentService.getServiceList(appointmentId);
	}
	
	@PutMapping("/appointment/cancel/{aid}")
	public String cancelAppointments(@PathVariable long aid,HttpServletRequest request) throws AppointmentException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return iAppointmentService.cancelAppointment(aid);
	}
	
	@PostMapping("/appointment/{cid}/{aid}")
	public Appointment bookAppointment(@RequestBody Payment payment,@PathVariable long aid,@PathVariable Integer cid,HttpServletRequest request)throws AppointmentException,CustomerNotFoundException, JwtTokenMalformedException, JwtTokenMissingException{
		JWTUtils.validateToken(request);
		return iAppointmentService.bookAppointment(aid, cid, payment);
	}
	@PutMapping("/appointment/complete/{aid}")
	public String completeAppointment(@PathVariable long aid,HttpServletRequest request) throws AppointmentException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return iAppointmentService.completeAppointment(aid);
	}

}
