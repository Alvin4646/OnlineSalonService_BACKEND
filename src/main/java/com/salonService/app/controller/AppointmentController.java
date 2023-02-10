package com.salonService.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.Appointment;
import com.salonService.app.exception.AppointmentException;

import com.salonService.app.services.IAppointmentService;

@RestController
public class AppointmentController {
	@Autowired
	private IAppointmentService iAppointmentService;
	@PostMapping("/appointment/{id}")
	public Appointment addAppointmentToCustomer(@RequestBody Appointment appointment,@PathVariable int id)throws Exception {
		return this.iAppointmentService.addAppointmentToCustomer(appointment, id);
	}
	@DeleteMapping("/deleteAppointment/{id}")
	public String deleteAppointment(@PathVariable Long id) {
		return iAppointmentService.removeAppointment(id);
	}
	@GetMapping("/appointmentById/{id}")
	public Appointment findAppointmentById(@PathVariable Long id)throws Exception {
		return iAppointmentService.getAppointmentById(id);
	}
	@GetMapping("/appointments")
	public List<Appointment> findAllAppointments() {
		return iAppointmentService.getAllAppointments();
	}
	@GetMapping("/appointments/{date}")
	public List<Appointment> findAppointmentByDate(@PathVariable String date ) {
		LocalDate dateToFind = LocalDate.parse(date);
		return iAppointmentService.getAppointmentByDate(dateToFind);
	}
	@PutMapping("/updateAppointment/{id}")
	public String updateAppointments(@RequestBody Appointment appointment,@PathVariable Long id)throws AppointmentException {
		return iAppointmentService.updateAppointment(id, appointment);
	}
	@GetMapping("/openAppointments")
	public List<Appointment> getOpenAppointments(){
		return iAppointmentService.getOpenAppointments();
	}
	
	@PutMapping("/updateAppointmentDate/{id}")
	public Appointment updateAppointmentDate(@RequestBody LocalDate preferredDate,@PathVariable Long id)throws AppointmentException {
		return iAppointmentService.updateAppointmentDate(id, preferredDate);
	}
	
}
