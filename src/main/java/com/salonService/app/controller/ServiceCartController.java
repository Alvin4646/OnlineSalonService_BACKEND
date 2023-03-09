package com.salonService.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.CartNotFoundException;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.exception.ServiceAlreadyExistsException;
import com.salonService.app.repository.ISalonRepository;
import com.salonService.app.repository.IServiceCartRepository;
import com.salonService.app.services.IServiceCartService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")


public class ServiceCartController {

	@Autowired 
	IServiceCartService serviceCartService;
	IServiceCartRepository serviceRepo;  
 
	@PostMapping("/cart/add")
	public ServiceCart addToCart(@RequestBody SalonService service, Long id) throws CartNotFoundException, SalonServiceNotFoundException {
		return serviceCartService.addServiceToCart(service, id);
	}

	@DeleteMapping("/cart/delete/service/{serviceId}/{id}")
	public SalonService deleteServiceById(@PathVariable Long serviceId,@PathVariable Long id) {
		return serviceCartService.deleteServiceById(serviceId, id);
	}

	@GetMapping("/cart/{id}")
	public ServiceCart getCartById(@PathVariable long id) throws CartNotFoundException { 
		return serviceCartService.getServiceCartByid(id);
	}

	@GetMapping("/cart/all")
	public List<ServiceCart> findAllCart() throws SalonServiceNotFoundException, CartNotFoundException {
		return serviceCartService.getAllServicesInCart();
	}

//	@DeleteMapping("deleteCart/{id}")
//	public String deleteServiceById(@PathVariable long id) {
//		return serviceCartService.deleteServiceById(id, id);
//	}
//	

//	@PostMapping("/add_To_Cart_by_id")
//	public ServiceCart addServiceById(@PathVariable Long serviceId, Long id) {
//		return serviceCartService.addServiceById(serviceId,id);
//	}

}
