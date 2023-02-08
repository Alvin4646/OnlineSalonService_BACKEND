package com.salonService.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.repository.ISalonRepository;
import com.salonService.app.repository.IServiceCartRepository;

@Service 
public class IServiceCartServiceImpl implements IServiceCartService {

	@Autowired
	IServiceCartRepository serviceRepo;
	@Autowired
	ICustomerService iCustomerService;
	@Autowired
	ISalonService iSalonService;
	
	public ServiceCart addServiceToCart(SalonService service,Integer custId) throws SalonServiceNotFoundException {
		ServiceCart cart=null;
		Customer customer = iCustomerService.getCustomer(custId);
		if (customer==null) {
		//Exception
		}
		SalonService serviceToAdd = iSalonService.getService(service.getServiceId());
		if (serviceToAdd == null) {
		//Exception
		}
		 
		if (customer.getCart() == null) {
		 cart = new ServiceCart();
		cart.getServiceList().add(service);
		 
		cart = serviceRepo.save(cart);
		customer.setCart(cart);
		 
		} else {
		cart = customer.getCart();
		cart.getServiceList().add(service);
		}
		 
		return serviceRepo.save(cart);

	}
	@Override
	public String deleteServiceById(Long orderId) {
		serviceRepo.deleteById(orderId);
		return "Deleted successfully";
	}
	
	@Override
	public ServiceCart updateCartService(Long id, ServiceCart order) {
		Optional<ServiceCart>existingCart=serviceRepo.findById(id);
		if(!existingCart.isPresent()) {
			//Exception
		}
		ServiceCart updatedCart=existingCart.get();
		updatedCart.setAmount(order.getAmount());

		return updatedCart;
	}
	

	
	@Override
	public List<ServiceCart> getAllServicesInCart() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ServiceCart getServiceCartByid(Long orderId) {
		return serviceRepo.findById(orderId).get();
	}
	
	
}
