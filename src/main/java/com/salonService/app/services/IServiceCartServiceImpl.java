package com.salonService.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.CartNotFoundException;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.repository.IServiceCartRepository;

@Service 
public class IServiceCartServiceImpl implements IServiceCartService {

	@Autowired
	IServiceCartRepository serviceRepo;
	@Autowired
	ICustomerService iCustomerService;
	@Autowired
	ISalonService iSalonService; 
	
	
	

	@Override
	public ServiceCart addServiceToCart(SalonService service, Long cartId) throws CartNotFoundException, SalonServiceNotFoundException{
		int count=0;
		 Optional<ServiceCart> service2 =serviceRepo.findById(cartId);
		 if(service2.isPresent());	
		 ServiceCart service1= service2.get();
		 if(!service2.isPresent()) {
			 throw new CartNotFoundException("Cart with customer "+cartId+"not found to add service");
		 }
//		 for(SalonService a: iSalonService.getAllServices())
//			    if(service.getServiceId()==a.getServiceId()){
//			    	count++;
//			} 
//			if(count==0) {
//			   throw new SalonServiceNotFoundException("Service not available right now to add to the cart");	
//			}
		 
			service1.getServiceList().add(service); 
			service1.getServiceList().sort((a,b)->a.getServiceId().compareTo(cartId));
		Double amount1=serviceRepo.findById(cartId).get().getAmount();
		Double amount2=amount1+Double.parseDouble(service.getServicePrice());
		service1.setAmount(amount2);
		serviceRepo.save(service1);
		return service1;
	}
	
	
	@Override
	public SalonService deleteServiceById(Long serviceId,Long cartid) {
		if(serviceRepo.findById(cartid).isPresent());
		ServiceCart cart=serviceRepo.findById(cartid).get();
		SalonService serviceToRemove=null;
		for(SalonService service:cart.getServiceList()) {
			if(service.getServiceId()==serviceId) {
				serviceToRemove=service;
				break;
			}
		}
		if(serviceToRemove!=null) {
			Double amount1=serviceRepo.findById(cartid).get().getAmount();
			Double amount2=amount1-Double.parseDouble(serviceToRemove.getServicePrice());
			cart.setAmount(amount2);
			cart.getServiceList().remove(serviceToRemove);
			serviceRepo.save(cart);
		}
		return serviceToRemove;
	}



	@Override
	public String deleteCartById(Long orderId) throws CartNotFoundException {
//		serviceRepo.deleteById(orderId);
//		return "Deleted successfully";
		Optional<ServiceCart> cart = serviceRepo.findById(orderId);
		if (!cart.isPresent()) {
			throw new CartNotFoundException("cart with " + orderId + " not found");
		}
		else {
		   serviceRepo.deleteById(orderId);   
		   return "Deleted successfully"; 
		}
	}

	@Override
	public List<ServiceCart> getAllServicesInCart() throws SalonServiceNotFoundException, CartNotFoundException {
		List<ServiceCart> list=serviceRepo.findAll();
		if(list.isEmpty()) {
			throw new CartNotFoundException("No services found in the cart, Cart is Empty");
		}
		return serviceRepo.findAll();
	}
	
	@Override
	public ServiceCart getServiceCartByid(Long orderId) throws CartNotFoundException {
		Optional<ServiceCart> cart=serviceRepo.findById(orderId);
		if(cart.isEmpty()) {
			throw new CartNotFoundException("No cart with this id found");
		}
		return serviceRepo.findById(orderId).get();
	}

}
