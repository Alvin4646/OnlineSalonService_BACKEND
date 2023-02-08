package com.salonService.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.services.IServiceCartService;


@RestController
@RequestMapping("/servicecart")

public class ServiceCartController {
	
	@Autowired
	IServiceCartService serviceCartService;
	
//	@PostMapping("/addToCart")
//	public ServiceCart addToCart(@RequestBody ServiceCart serviceCart) {
//	return serviceCartService.addServiceToCart(serviceCart);	
//	}
//	
	@GetMapping("/getCart/{id}")
	public ServiceCart getCartById(@PathVariable long id) {
		return serviceCartService.getServiceCartByid(id);
	}

	@DeleteMapping("deleteCart/{id}")
	public String deleteServiceById(@PathVariable long id) {
		return serviceCartService.deleteServiceById(id);
	}
	
	@GetMapping("/getCart")
	public List<ServiceCart> findAllCart() {
		return serviceCartService.getAllServicesInCart();
	}
//	@PutMapping("/updateCart/{id}")
//	public String ServiceCart(@RequestBody Appointment appointment,@PathVariable long id) {
//		return serviceCartService.updateCartService(id, appointment);
//	}

}
