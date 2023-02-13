package com.salonService.app.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Payment;
import com.salonService.app.exception.AppointmentException;

import com.salonService.app.services.IAppointmentService;

@RestController
public class AppointmentController {
	@Autowired
	private IAppointmentService iAppointmentService;

//	@PostMapping("/appointment")
//	public Appointment addAppointment(@Valid @RequestBody Appointment appointment)throws Exception {
//		return this.iAppointmentService.addAppointment(appointment);
//	}
	@PostMapping("/appointment/{cid}")
	public Appointment addAppointmentToCustomer(@Valid @RequestBody Appointment appointment, @PathVariable int cid)
			throws Exception {
		return this.iAppointmentService.addAppointmentToCustomer(appointment, cid);
	}

//	@DeleteMapping("/deleteAppointment/{id}")
//	public String deleteAppointment(@PathVariable Long id)throws AppointmentException {
//		return iAppointmentService.removeAppointment(id);
//	}
	@GetMapping("/appointment/{aid}")
	public Appointment findAppointmentById(@PathVariable Long aid) throws AppointmentException {
		return iAppointmentService.getAppointmentById(aid);
	}

	@GetMapping("/appointments")
	public List<Appointment> findAllAppointments() {
		return iAppointmentService.getAllAppointments();

	}

	@GetMapping("/appointmentDt/{date}")
	public List<Appointment> findAppointmentByDate(@PathVariable String date) {
		LocalDate dateToFind = LocalDate.parse(date);
		return iAppointmentService.getAppointmentByDate(dateToFind);
	}

	@PutMapping("/Appointment/{id}")
	public Appointment updateAppointments(@Valid @RequestBody Appointment appointment, @PathVariable Long id)
			throws AppointmentException {
		return iAppointmentService.updateAppointment(id, appointment);
	}

	@GetMapping("/openAppointments")
	public List<Appointment> getOpenAppointments() {
		return iAppointmentService.getOpenAppointments();
	}

	@PutMapping("/updateAppointmentDate/{id}")
	public Appointment updateAppointmentDate(@RequestBody LocalDate preferredDate, @PathVariable Long id)
			throws AppointmentException {
		return iAppointmentService.updateAppointmentDate(id, preferredDate);
	}

}
