package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.Customer;
import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.SalonServiceNotFoundException;

public interface IServiceCartService {
	public ServiceCart addServiceToCart(SalonService service,int custId)throws SalonServiceNotFoundException ;

	public String deleteServiceById(Long orderId) ;

	public ServiceCart updateCartService(Long orderId, ServiceCart order) ;

	public ServiceCart getServiceCartByid(Long orderId);

	public List<ServiceCart> getAllServicesInCart() ;
}
