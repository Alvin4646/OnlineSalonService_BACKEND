package com.salonService.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.exception.ServiceAlreadyExistsException;
import com.salonService.app.entity.SalonService;
import com.salonService.app.repository.ISalonRepository;
//import com.salonService.app.Exception.ServiceAlreadyExistsException;

@Service
@Transactional

public class SalonServiceImpl implements ISalonService{
	@Autowired
	private ISalonRepository salonRepository;
	@Override
	public SalonService addService(SalonService salonService) throws ServiceAlreadyExistsException {
		Optional<SalonService> salon = salonRepository.findById(salonService.getServiceId());
    	if(salon.isPresent()) {
    		throw new ServiceAlreadyExistsException("SERVICE ALREADY EXISTS");
    	}
		salonRepository.save(salonService);
		return salonService;
		
	}

	@Override
	public void removeService(Long serviceId) throws SalonServiceNotFoundException {
		Optional<SalonService> salon = salonRepository.findById(serviceId);
		salon.orElseThrow(() -> new SalonServiceNotFoundException("Salon Service NOT FOUND"));
		salonRepository.deleteById(serviceId);
		
	}

	@Override
	public SalonService updateService(Long serviceId, SalonService salonService) throws SalonServiceNotFoundException {
		Optional<SalonService> salon = salonRepository.findById(serviceId);
		salonService = salon.orElseThrow(() -> new SalonServiceNotFoundException("Salon Service NOT FOUND"));
		salonService.setSeviceName(salonService.getSeviceName());
       return salonService;
	}

	@Override
	public SalonService getService(Long serviceId) throws SalonServiceNotFoundException {
		Optional<SalonService> optional = salonRepository.findById(serviceId);
		SalonService s = optional.orElseThrow(() -> new SalonServiceNotFoundException("Service SalonService NOT FOUND"));
		return s;
	}

	@Override
	public List<SalonService> getAllServices() throws SalonServiceNotFoundException {
		Iterable<SalonService> order2 = salonRepository.findAll(); 
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(order -> {
			s3.add(order);
		});
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Salon Service NOT FOUND");
		return s3;
	}

	@Override
	public List<SalonService> getSeviceByName(String seviceName) throws SalonServiceNotFoundException {
		Iterable<SalonService> order2 = salonRepository.findBySeviceName(seviceName); 
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(order -> {
			s3.add(order);
		});
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Salon Service NOT FOUND");
		return s3;
	}
//
	@Override
	public List<SalonService> getServiceByPrice(String servicePrice) throws SalonServiceNotFoundException {
		Iterable<SalonService> order2 = salonRepository.findByServicePrice(servicePrice); 
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(order -> {
			s3.add(order);
		});
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Salon Service NOT FOUND");
		return s3;
	}

	@Override
	public List<SalonService> getServicesByDuration(String serviceDuration) throws SalonServiceNotFoundException {
		Iterable<SalonService> order2 = salonRepository.findByServiceDuration(serviceDuration);
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(order -> {
			s3.add(order);
		});
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Salon Service NOT FOUND");
		return s3;
	}

	

}
