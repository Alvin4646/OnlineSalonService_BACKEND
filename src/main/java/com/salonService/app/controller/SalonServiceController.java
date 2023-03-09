package com.salonService.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.SalonService;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.services.ISalonService;

@RestController
//@RequestMapping(value = "/SalonService")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class SalonServiceController {

	@Autowired
	private ISalonService salonService;

	


	@GetMapping(value = "/{serviceId}")
	// public ResponseEntity<SalonService> getService(@PathVariable Long serviceId)
	// throws SalonServiceNotFoundException {
	public SalonService getService(@PathVariable Long serviceId) throws SalonServiceNotFoundException {

		return salonService.getService(serviceId);
		// return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping(value = "/services")
	public ResponseEntity<List<SalonService>> getAllServices() throws SalonServiceNotFoundException {
		List<SalonService> s = salonService.getAllServices();

		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/service/{seviceName}")
	public ResponseEntity<List<SalonService>> getSeviceByName(@PathVariable String seviceName)
			throws SalonServiceNotFoundException {
		List<SalonService> s = salonService.getSeviceByName(seviceName);

		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping(value = "/service/ServicePrice/{servicePrice}")
	public ResponseEntity<List<SalonService>> getServiceByPrice(@PathVariable String servicePrice)
			throws SalonServiceNotFoundException {
		List<SalonService> s = salonService.getServiceByPrice(servicePrice);

		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping(value = "/service/ServiceDuration/{serviceDuration}")
	public ResponseEntity<List<SalonService>> getServiceByDuration(@PathVariable String serviceDuration)
			throws SalonServiceNotFoundException {
		List<SalonService> s = salonService.getServicesByDuration(serviceDuration);

		return new ResponseEntity<>(s, HttpStatus.OK);
	}

}
