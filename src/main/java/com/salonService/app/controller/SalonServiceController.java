package com.salonService.app.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.exception.ServiceAlreadyExistsException;
import com.salonService.app.entity.SalonService;
import com.salonService.app.services.ISalonService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
//@RequestMapping(value = "/SalonService")
@Validated

public class SalonServiceController {

	@Autowired
	private ISalonService salonService;

	@Autowired
	private Environment environment;

//	@DeleteMapping(value = "/deleteService/{serviceId}")
//	public ResponseEntity<String> removeService(@PathVariable Long serviceId) throws SalonServiceNotFoundException {
//		salonService.removeService(serviceId);
//		String successMessage = environment.getProperty("serviceDeletedSuccessfully");
//
//		return new ResponseEntity<>(successMessage, HttpStatus.OK);
//	}
//
//	@PutMapping(value = "/updateService/{serviceId}")
//	public ResponseEntity<String> updateService(@PathVariable Long serviceId, @RequestBody SalonService service)
//			throws SalonServiceNotFoundException, Exception {
//		salonService.updateService(serviceId, service);
//		String successMessage = environment.getProperty("Updated Successfully");
//
//		return new ResponseEntity<>(successMessage, HttpStatus.OK);
//	}
//
//	@PostMapping(value = "/addService")
//	public ResponseEntity<String> addService(@Valid @RequestBody SalonService s) throws ServiceAlreadyExistsException {
//		salonService.addService(s);
//		String successMessage = environment.getProperty("service successfully");
//		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
//
//		// getProperty:- The Java platform uses a Properties object to provide
//		// information about the local system and configuration.
//		// Class Environment:- A container (holder) for an exception that is used in
//		// Request operations to make exceptions available to the client.
//	}

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
