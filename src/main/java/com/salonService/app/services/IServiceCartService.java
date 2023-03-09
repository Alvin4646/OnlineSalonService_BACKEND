package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.CartNotFoundException;
import com.salonService.app.exception.SalonServiceNotFoundException;

public interface IServiceCartService {
	public ServiceCart getServiceCartByid(Long orderId) throws CartNotFoundException;
	public List<ServiceCart> getAllServicesInCart() throws SalonServiceNotFoundException, CartNotFoundException ;
	public ServiceCart addServiceToCart(SalonService service,Long id) throws CartNotFoundException, SalonServiceNotFoundException;
	public SalonService deleteServiceById(Long serviceId, Long id);
	
	
	
	public String deleteCartById(Long orderId) throws CartNotFoundException ;
	//public ServiceCart addServiceById(Long serviceId, Long id);
	

}
