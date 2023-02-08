package com.salonService.app.controller;

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
import com.salonService.app.services.IAppointmentService;

@RestController
public class AppointmentController {
	@Autowired
	private IAppointmentService iAppointmentService;
	@PostMapping("/appointment")
	public Appointment addAppointment(@RequestBody Appointment appointment) {
		return this.iAppointmentService.addAppointment(appointment);
	}
	@DeleteMapping("/deleteAppointment/{id}")
	public String deleteAppointment(@PathVariable int id) {
		return iAppointmentService.removeAppointment(id);
	}
	@GetMapping("/appointmentById/{id}")
	public Appointment findAppointmentById(@PathVariable long id) {
		return iAppointmentService.getAppointmentById(id);
	}
	@GetMapping("/appointments")
	public List<Appointment> findAllEmployee() {
		return iAppointmentService.getAllAppointments();
	}
	@PutMapping("/updateAppointment/{id}")
	public String updateEmployee(@RequestBody Appointment appointment,@PathVariable long id) {
		return iAppointmentService.updateAppointment(id, appointment);
	}
}
