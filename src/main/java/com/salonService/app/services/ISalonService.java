package com.salonService.app.services;

import java.util.List;

import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.exception.ServiceAlreadyExistsException;
import com.salonService.app.entity.SalonService;

public interface ISalonService {
	public SalonService addService(SalonService salonService) throws ServiceAlreadyExistsException ;

	public void removeService(Long serviceId) throws SalonServiceNotFoundException ;

	public SalonService updateService(Long serviceId, SalonService salonService) throws SalonServiceNotFoundException, Exception ;

	public SalonService getService(Long serviceId) throws SalonServiceNotFoundException ;

	public List<SalonService> getAllServices() throws SalonServiceNotFoundException ;

	public List<SalonService> getSeviceByName(String seviceName) throws SalonServiceNotFoundException ;

	public List<SalonService> getServiceByPrice(String servicePrice) throws SalonServiceNotFoundException ;

	public List<SalonService> getServicesByDuration(String serviceDuration) throws SalonServiceNotFoundException ;
}
