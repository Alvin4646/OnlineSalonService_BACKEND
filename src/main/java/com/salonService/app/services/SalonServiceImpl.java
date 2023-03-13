package com.salonService.app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.exception.ServiceAlreadyExistsException;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.ISalonRepository;
import com.salonService.app.repository.IServiceCartRepository;


@Service
@Transactional

public class SalonServiceImpl implements ISalonService {
	@Autowired
	private ISalonRepository salonRepository;
	@Autowired
	private IServiceCartRepository serviceCartRepository;
	@Autowired
	private IAppointmentRepository iappointmentRepo;
	@Override 
	public SalonService addService(SalonService salonService) throws ServiceAlreadyExistsException {
		Optional<SalonService> salon = salonRepository.findById(salonService.getServiceId());
		if (salon.isPresent()) {
			throw new ServiceAlreadyExistsException("SERVICE ALREADY EXISTS");
		}
		salonRepository.save(salonService);
		return salonService;
	}

	@Override
	public void removeService(Long serviceId) throws SalonServiceNotFoundException {
		Iterable<ServiceCart> cart = serviceCartRepository.findAll();
		cart.forEach((cart1) -> {
			Optional<SalonService> service = salonRepository.findById(serviceId);
			if (service.isPresent()) {
				SalonService service1 = service.get();
				if (cart1.getServiceList().contains(service1)) {
					int count = Collections.frequency(cart1.getServiceList(), service1);
					Double amount1 = serviceCartRepository.findById(cart1.getId()).get().getAmount();
					Double amount2 = amount1 - count * Double.parseDouble(service1.getServicePrice());
					cart1.setAmount(amount2);
					for (int i = 0; i < count; i++) {
						cart1.getServiceList().remove(service1);
					}
					serviceCartRepository.save(cart1);
				}
			}
		});
		
		Iterable<Appointment> appointment = iappointmentRepo.findAll();
		appointment.forEach((appointment1) -> {
			Optional<SalonService> service = salonRepository.findById(serviceId);
			if (service.isPresent()) {
				SalonService service1 = service.get();
				if (appointment1.getServiceList().contains(service1)) {
					int count = Collections.frequency(appointment1.getServiceList(), service1);

					for (int i = 0; i < count; i++) {
						appointment1.getServiceList().remove(service1);
					}
					iappointmentRepo.save(appointment1);
				}
			}
		});
		
		
		
		
		Optional<SalonService> salon = salonRepository.findById(serviceId);
		salon.orElseThrow(() -> new SalonServiceNotFoundException("Salon Service NOT FOUND with id " + serviceId));
		salonRepository.deleteById(serviceId);
	}

	@Override
	public SalonService updateService(Long serviceId, SalonService salonService) throws SalonServiceNotFoundException {
		Optional<SalonService> salon = salonRepository.findById(serviceId);
		if (salon.isPresent()) {
			SalonService service = salon.get();
			service.setSeviceName(salonService.getSeviceName());
			service.setServicePrice(salonService.getServicePrice());
			service.setServiceDuration(salonService.getServiceDuration());
			return salonRepository.save(service);
		} else {
			throw new SalonServiceNotFoundException("Salon service not found");
		}

	}

	@Override
	public SalonService getService(Long serviceId) throws SalonServiceNotFoundException {
		Optional<SalonService> optional = salonRepository.findById(serviceId);
		return optional.orElseThrow(() -> new SalonServiceNotFoundException("Salon Service NOT FOUND"));
	}

	@Override
	public List<SalonService> getAllServices() throws SalonServiceNotFoundException {
		Iterable<SalonService> order2 = salonRepository.findAll();
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(s3::add);
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Salon Service NOT FOUND");
		return s3;
	}

	@Override
	public List<SalonService> getSeviceByName(String seviceName) throws SalonServiceNotFoundException {
		Iterable<SalonService> order2 = salonRepository.findBySeviceName(seviceName);
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(s3::add);
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Salon Service NOT FOUND with name " + seviceName);
		return s3;
	}

	@Override
	public List<SalonService> getServiceByPrice(String servicePrice) throws SalonServiceNotFoundException {
		Iterable<SalonService> order2 = salonRepository.findByServicePrice(servicePrice);
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(s3::add);
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Salon Service NOT FOUND with price " + servicePrice);
		return s3;
	}

	@Override
	public List<SalonService> getServicesByDuration(String serviceDuration) throws SalonServiceNotFoundException {
		Iterable<SalonService> order2 = salonRepository.findByServiceDuration(serviceDuration);
		List<SalonService> s3 = new ArrayList<>();
		order2.forEach(s3::add);
		if (s3.isEmpty())
			throw new SalonServiceNotFoundException("Salon Service NOT FOUND with duration " + serviceDuration);
		return s3;
	}

}
